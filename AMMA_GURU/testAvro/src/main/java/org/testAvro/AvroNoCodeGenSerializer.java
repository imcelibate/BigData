package org.testAvro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import example.avro.User;

/**
     Created by imcelibate
     on Nov 18, 2017
*/

public class AvroNoCodeGenSerializer {
	
	public static void serialize(GenericRecord user4, GenericRecord user5, Schema schema, String path){
		try {
			DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
			DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
			dataFileWriter.create(schema, new File(path));
			dataFileWriter.append(user4);
			dataFileWriter.append(user5);
			dataFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


