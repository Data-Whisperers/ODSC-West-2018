/*
 * Copyright 2018 Data Whisperers LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datawhisperers.kafkaconsumermeetup.main;

import com.datawhisperers.kafkaconsumermeetup.pojo.Meetup;
import com.datawhisperers.kafkaconsumermeetup.pojo.Venue;
import com.datawhisperers.kafkaconsumermeetup.pojo.CountryVenue;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.commons.cli.ParseException;
import org.slf4j.LoggerFactory;

public class MeetupConsumer {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MeetupConsumer.class);

    private static final String TOPICNAME = "topicname";
    private static final String GROUPID = "groupid";
    private static final String METADATABROKERLIST = "metadatabrokerlist";

    private CountryVenue countryVenues = new CountryVenue();

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
            Date dt = new Date();
            System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<");
            System.out.println("Refresh:" + sdf.format(dt));
            countryVenues.getCountryVenue().keySet().forEach((key) -> {
                System.out.println("Country key: " + key + " Size: " + countryVenues.getCountryVenue().get(key).size());
            });
            dt = new Date();
            System.out.println("Refreshed:" + sdf.format(dt));
            System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<");
        }
    };

    /**
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        Options options = new Options();

        options.addOption((org.apache.commons.cli.Option.builder(TOPICNAME))
                .required(true)
                .longOpt(TOPICNAME)
                .desc("Kafka topic name")
                .hasArg()
                .build());

        options.addOption((org.apache.commons.cli.Option.builder(METADATABROKERLIST))
                .required(true)
                .longOpt(METADATABROKERLIST)
                .desc("Kafka metadata.broker.list")
                .hasArg()
                .build());

        options.addOption((org.apache.commons.cli.Option.builder(GROUPID))
                .required(true)
                .longOpt(GROUPID)
                .desc("Kafka Group ID")
                .hasArg()
                .build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("com.svds.tailer2kafka.main.Main", options);

            throw new IOException("Missing Args");
        }

        MeetupConsumer meetupConsumer = new MeetupConsumer();
        meetupConsumer.consume(cmd);
    }

    public void consume(CommandLine cmd) {

        Timer timer = new Timer();
        timer.schedule(task, new Date(), 15000);
        KafkaConsumer<String, String> consumer;

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", cmd.getOptionValue(METADATABROKERLIST));
        properties.setProperty("group.id", cmd.getOptionValue(GROUPID));
        properties.setProperty("client.id", "MeetupConsumer");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        properties.setProperty("auto.offset.reset", "earliest");

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(cmd.getOptionValue(TOPICNAME)));

        int count = 0;
        Duration timeout = Duration.ofMillis(200);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            records.forEach(record -> processRecords(record));
        }
    }

    private void processRecords(ConsumerRecord<String, String> record) {
        try {
            Meetup meetup = JsonToMeetup.jsonToMeetup(record.value());
            LOG.info(meetup.toString());
            addCountries(countryVenues, meetup.getGroup().getGroup_country(), meetup.getVenue());

        } catch (Exception e) {
            LOG.error("<<<<<<<<<<<<<ERROR>>>>>>>>>>>>>>");
            LOG.error("Raw: " + record.value());
            LOG.error("<<<<<<<<<<<<<ERROR>>>>>>>>>>>>>>", e);
        }

    }

    private void addCountries(CountryVenue countryVenues, String country, Venue venue) {

        if (!countryVenues.getCountryVenue().containsKey(country)) {

            countryVenues.getCountryVenue().put(country, new TreeMap<>());
        }

        if (countryVenues.getCountryVenue().containsKey(country)) {
            countryVenues.getCountryVenue().get(country).put(venue.getVenue_id(), venue);
        }

    }

}
