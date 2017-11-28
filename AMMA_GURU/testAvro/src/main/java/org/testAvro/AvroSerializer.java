package org.testAvro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import example.avro.User;

/**
     Created by imcelibate
     on Nov 18, 2017
*/

public class AvroSerializer {
	
	public static void serializeAvro(User user1,User user2, User user3,String path){
		try {
			DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
			DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
			dataFileWriter.create(user1.getSchema(), new File(path));
			dataFileWriter.append(user1);
			dataFileWriter.append(user2);
			dataFileWriter.append(user3);
			dataFileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


