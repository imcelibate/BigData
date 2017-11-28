#!/usr/bin/env bash

cd /home/imcelibate

# Create a topic
kafka/bin/kafka-topics.sh --create \
  --zookeeper localhost:2181 \
  --replication-factor 3 \
  --partitions 13 \
  --topic first-simple-topic
