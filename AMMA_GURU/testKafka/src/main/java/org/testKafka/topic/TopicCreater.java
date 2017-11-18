package org.testKafka.topic;

import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.*;
import kafka.utils.ZkUtils;


/**
     Created by imcelibate
     on Oct 31, 2017
*/

public class TopicCreater {
	
	public static void createTopic(String topic){
		String zookeeperConnect = "localhost:2181"; //eg : "zkserver1:2181,zkserver2:2181";
	    int sessionTimeoutMs = 10 * 1000;
	    int connectionTimeoutMs = 8 * 1000;
	    
	    int partitions = 14;
	    int replication = 1;
	    Properties topicConfig = new Properties(); // add per-topic configurations settings here
	    ZkClient zkClient = new ZkClient(zookeeperConnect, sessionTimeoutMs, connectionTimeoutMs, ZKStringSerializer$.MODULE$);
	    
	    
	    // Security for Kafka was added in Kafka 0.9.0.0
	    boolean isSecureKafkaCluster = false;

	    ZkUtils zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperConnect), isSecureKafkaCluster);
	    AdminUtils.createTopic(zkUtils, topic, partitions, replication, topicConfig, RackAwareMode.Enforced$.MODULE$);
	    zkClient.close();
	    
	}

}


