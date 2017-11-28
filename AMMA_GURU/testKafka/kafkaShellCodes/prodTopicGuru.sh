#!/usr/bin/env bash
cd /home/imcelibate

kafka/bin/kafka-console-producer.sh \
    --broker-list localhost:9092 \
    --topic my-guru-topic
