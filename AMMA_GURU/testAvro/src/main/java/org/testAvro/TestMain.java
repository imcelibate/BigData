package org.testAvro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;

import example.avro.User;

/**
     Created by imcelibate
     on Nov 18, 2017
*/

public class TestMain {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setName("GURU");
		user1.setFavoriteNumber(999);
		// Leave favorite color null

		// Alternate constructor
		User user2 = new User("KALI",4444, "BLACK");

		// Construct via builder
		User user3 = User.newBuilder()
		             .setName("AMMA")
		             .setFavoriteColor("RED")
		             .setFavoriteNumber(null)
		             .build();
		
		String path = "/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/users.avro";
		System.out.println("AMMA staring serializing ....");
		AvroSerializer.serializeAvro(user1, user2, user3, path);
		System.out.println("AMMA Complete serializing ....");
		
		System.out.println("AMMA staring DeSerializing ....");
		AvroDeSerializer.deSerialzer(path);
		System.out.println("AMMA Complete DeSerializing ....");
		
		System.out.println("---------AMMAGURU--------------");
		System.out.println("--------Test NO code Generation example--------------");
		try {
			Schema schema = new Schema.Parser().parse(new File("/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/testNoCodeGen.avsc"));  

			GenericRecord user4 = new GenericData.Record(schema);
			user4.put("name", "AMMA AGAIN");
			user4.put("favorite_number", 999);
			user4.put("favorite_color", "RED");

			GenericRecord user5 = new GenericData.Record(schema);
			user5.put("name", "GURU AGAIN");
			user5.put("favorite_number", 7);
			user5.put("favorite_color", "WHITE");
			
			path = "/home/imcelibate/AMMA/tech/BIGData/MapReduce/workspace/SampleData/output/testNoCodeGen.avro";
			
			System.out.println("AMMA staring serializing ....");
			AvroNoCodeGenSerializer.serialize(user4, user5, schema, path);
			System.out.println("AMMA Complete serializing ....");
			
			System.out.println("AMMA staring DeSerializing ....");
			AvroDeSerializer.deSerialzer(path);
			System.out.println("AMMA Complete DeSerializing ....");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		        

	}

}


