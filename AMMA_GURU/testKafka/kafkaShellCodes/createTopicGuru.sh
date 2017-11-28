#!/usr/bin/env bash

cd /home/imcelibate

# Create a topic
kafka/bin/kafka-topics.sh --create \
  --zookeeper localhost:2181 \
  --replication-factor 1 --partitions 13 \
  --topic my-guru-topic
