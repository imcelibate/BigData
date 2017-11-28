#!/usr/bin/env bash
cd /home/imcelibate

kafka/bin/kafka-console-consumer.sh \
    --bootstrap-server localhost:9092 \
    --zookeeper localhost:2181 \
    --topic my-guru-topic \
    --from-beginning
