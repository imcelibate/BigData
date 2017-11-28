#!/usr/bin/env bash

cd /home/imcelibate/kafka

# List existing topics
bin/kafka-server-start.sh config/server-0.properties &
bin/kafka-server-start.sh config/server-1.properties &
bin/kafka-server-start.sh config/server-2.properties &
