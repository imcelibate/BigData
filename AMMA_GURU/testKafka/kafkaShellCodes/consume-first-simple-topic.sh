#!/usr/bin/env bash
cd /home/imcelibate

kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server localhost:9092,localhost:9093,localhost:9094 \
    --zookeeper localhost:2181 \
    --topic first-simple-topic \
    --from-beginning
