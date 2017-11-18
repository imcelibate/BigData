package org.testKafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.testKafka.topic.TopicCreater;


/**
     Created by imcelibate
     on Oct 29, 2017
*/

public class FirstSimpleProducer {
	
    private final static String TOPIC = "first-GURU-topic";
    private final static String BOOTSTRAP_SERVERS ="localhost:9092,localhost:9093,localhost:9094";
    
    private static Producer<Long, String> createProducer(){
    	
    	Properties props = new Properties(); 
    	props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "FirstSimpleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());  
        
        return new KafkaProducer<Long, String>(props);
    	
    }
    
    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            for (long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record =
                        new ProducerRecord<Long, String>(TOPIC, index,
                                    "AMMA GURU OM AMMA !!" + index);

                RecordMetadata metadata = producer.send(record).get();

                long elapsedTime = System.currentTimeMillis() - time;
                System.out.printf("sent record(key=%s value=%s) " +
                                "meta(partition=%d, offset=%d) time=%d\n",
                        record.key(), record.value(), metadata.partition(),
                        metadata.offset(), elapsedTime);

            }
        } finally {
            producer.flush();
            producer.close();
        }
    }
    
    public static void main(String[] args){
    	try {
    		//TopicCreater.createTopic(TOPIC);
			FirstSimpleProducer.runProducer(20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

}


