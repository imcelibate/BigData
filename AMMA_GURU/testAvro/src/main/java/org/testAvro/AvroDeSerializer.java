package org.testAvro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import example.avro.User;

/**
     Created by imcelibate
     on Nov 18, 2017
*/

public class AvroDeSerializer {
	
	public static void deSerialzer(String file){
		try {
			// Deserialize Users from disk
			DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
			DataFileReader<User> dataFileReader = new DataFileReader<User>(new File(file), userDatumReader);
			User user = null;
			while (dataFileReader.hasNext()) {
			// Reuse user object by passing it to next(). This saves us from
			// allocating and garbage collecting many objects for files with
			// many items.
			user = dataFileReader.next(user);
			System.out.println(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


