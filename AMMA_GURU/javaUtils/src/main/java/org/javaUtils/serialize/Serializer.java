package org.javaUtils.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
     Created by imcelibate
     on Nov 6, 2017
*/

public class Serializer {
	
	public static void serialize(){
        SerializePojo object = new SerializePojo("AMMA", 999, 4444, 7);
        String filename = "//home//imcelibate//AMMA//tech//Data//serialize.txt";
 
        // Serialization
        try {
 
            // Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
 
            // Method for serialization of object
            out.writeObject(object);
 
            out.close();
            file.close();
 
            System.out.println("Object has been serialized\n"
                              + "Data before Deserialization.");
            printdata(object);
 
            // value of static variable changed
            object.b = 2000;	                
        }
	 
    catch (IOException ex) {
        System.out.println("IOException is caught");
    }

  }
	
	
	public static void printdata(SerializePojo object1)
    {
 
        System.out.println("name = " + object1.name);
        System.out.println("age = " + object1.age);
        System.out.println("a = " + object1.a);
        System.out.println("b = " + object1.b);
    }
}


