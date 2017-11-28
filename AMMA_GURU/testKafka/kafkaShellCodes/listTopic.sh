#!/usr/bin/env bash

cd /home/imcelibate

# List existing topics
kafka/bin/kafka-topics.sh --list \
    --zookeeper localhost:2181
