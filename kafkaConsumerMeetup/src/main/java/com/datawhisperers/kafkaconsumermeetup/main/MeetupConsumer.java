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
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
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

    /**
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here
        
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

        KafkaConsumer<String, String> consumer;

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", cmd.getOptionValue(METADATABROKERLIST));
        properties.setProperty("group.id", cmd.getOptionValue(GROUPID));
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        properties.setProperty("auto.offset.reset", "earliest");

        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(cmd.getOptionValue(TOPICNAME)));

        // Loop until ctrl + c
        int count = 0;
        Duration timeout = Duration.ofMillis(200);
        while (true) {
            // Poll for records
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            // Did we get any?
            if (records.count() == 0) {
                LOG.info("No Records!!!");
            } else {
                // Yes, loop over records
                for (ConsumerRecord<String, String> record : records) {
                    // Display record and count
                    count += 1;
                    LOG.info(count + ": " + record.value());
                    try {
                        Meetup meetup = JsonToMeetup.jsonToMeetup(record.value());
                        LOG.info(meetup.toString());
                        
                    } catch (Exception e) {
                        LOG.error("<<<<<<<<<<<<<ERROR>>>>>>>>>>>>>>",e);

                    }
                }
            }
        }
    }

}
