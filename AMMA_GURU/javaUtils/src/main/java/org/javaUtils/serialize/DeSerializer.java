package org.javaUtils.serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
     Created by imcelibate
     on Nov 6, 2017
*/

public class DeSerializer {
	
	
	public static void deSerialize(){
		
		SerializePojo object = null;
		String filename = "//home//imcelibate//AMMA//tech//Data//serialize.txt";
		 
        // Deserialization
        try {
 
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
 
            // Method for deserialization of object
            object = (SerializePojo)in.readObject();
 
            in.close();
            file.close();
            System.out.println("Object has been deserialized\n"
                                + "Data after Deserialization.");
            Serializer.printdata(object);
 
            // System.out.println("z = " + object1.z);
        }
 
        catch (IOException ex) {
            System.out.println("IOException is caught");
        }
 
        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" +
                                " is caught");
        }
		
	}

}


