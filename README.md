# ODSC-West-2018
Open Data Science Conference West 2018 code examples

# Install Everything
```shell
mkdir ~/ODSC-DW-demo
cd ~/ODSC-DW-demo
wget http://mirror.reverse.net/pub/apache/kafka/2.0.0/kafka_2.11-2.0.0.tgz 
tar -xzf kafka_2.11-2.0.0.tgz

git clone https://github.com/Data-Whisperers/ODSC-West-2018.git
cd ODSC-West-2018/tailer2kafka/
mvn clean install
cd ../kafkaConsumerMeetup/
mvn clean install
```

# Run Everything
- **Kafka**
  - Window 1: `cd ~/ODSC-DW-demo/kafka_2.11-2.0.0;bin/zookeeper-server-start.sh config/zookeeper.properties`
  - Window 2: `cd ~/ODSC-DW-demo/kafka_2.11-2.0.0;bin/kafka-server-start.sh config/server.properties`
  - Window 3: `cd ~/ODSC-DW-demo/kafka_2.11-2.0.0;bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic meetupstream`
- **Meetup Kafka demo**
  - Window 1: `cd ~/ODSC-DW-demo/ODSC-West-2018/tailer2kafka;bin/run_curl_meetup_stream.sh`
  - Window 2: `cd ~/ODSC-DW-demo/ODSC-West-2018/tailer2kafka;bin/run_tailer2kafka.sh`
  - Window 3: `cd ~/ODSC-DW-demo/ODSC-West-2018/kafkaConsumerMeetup;bin/run_kafkaConsumerMeetup.sh`
  - Window 4: `tail -F /var/tmp/meetupstream`
  - Window 5: `tail -F /var/tmp/tailer2kafka.log`
  - Window 6: `tail -F /var/tmp/kafkaConsumerMeetup.log`
