Zookeeper:
zookeeper-server-start.bat D:\Software\akz\kafka_2.12-2.7.0\config\zookeeper.properties

Kafka:
kafka-server-start.bat D:\Software\akz\kafka_2.12-2.7.0\config\server.properties


create topic:
bin/kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test


Producer:
kafka-console-producer.bat --broker-list localhost:9092 --topic lumenTopic


Consumer:
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic lumenTopic

list of topics:
kafka-topics.bat --bootstrap-server localhost:9092 --list

delete topic:
kafka-topics.bat --zookeeper localhost:2181 --topic javatechie --delete

D:\Software\akz\kafka_2.12-2.7.0\bin\windows>kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic lumenTopic

Consumer list:
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
the group will be shown.

Consumer group:
kafka-consumer-groups --bootstrap-server <kafkahost:port> --topic lumenTopic --reset-offsets --to-earliest

chear sheet:
https://medium.com/@TimvanBaarsen/apache-kafka-cli-commands-cheat-sheet-a6f06eac01b

