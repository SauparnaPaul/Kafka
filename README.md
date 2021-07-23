Zookeeper:
zookeeper-server-start.bat D:\Software\akz\kafka_2.12-2.7.0\config\zookeeper.properties

Kafka:
kafka-server-start.bat D:\Software\akz\kafka_2.12-2.7.0\config\server.properties
kafka-server-start.bat D:\Software\akz\kafka_Node2\config\server.properties


create topic:
bin/kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

bin/kafka-topics.bat --create  --bootstrap-server localhost:9092,localhost:9094 --replication-factor 2 --partitions 2 --topic testP2

Producer:
kafka-console-producer.bat --broker-list localhost:9092 --topic lumenTopic

kafka-console-producer.bat --broker-list localhost:9092,localhost:9094 --topic testP2
kafka-console-producer.bat --broker-list localhost:9092,localhost:9094 --topic topicWithPartition



Consumer:
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic lumenTopic

kafka-console-consumer.bat --bootstrap-server localhost:9092,localhost:9094 --topic testP2

list of topics:
kafka-topics.bat --bootstrap-server localhost:9092 --list

delete topic:
kafka-topics.bat --zookeeper localhost:2181 --topic topicname --delete

D:\Software\akz\kafka_2.12-2.7.0\bin\windows>kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic lumenTopic

Consumer list:
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
the group will be shown.

Consumer group:
kafka-consumer-groups --bootstrap-server <kafkahost:port> --topic lumenTopic --reset-offsets --to-earliest

chear sheet:
https://medium.com/@TimvanBaarsen/apache-kafka-cli-commands-cheat-sheet-a6f06eac01b
netstat -ano|findstr "8080"
taskkill /F /PID 1696

