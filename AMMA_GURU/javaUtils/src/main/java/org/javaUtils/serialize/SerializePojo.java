package org.javaUtils.serialize;

import java.io.Serializable;

/**
 * Created by imcelibate on Nov 6, 2017
 */

public class SerializePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -284185247239695831L; 
 
	
	//A transient variable is a variable that may not be serialized. 
	//You use the transient keyword to indicate to the Java virtual machine 
	//that the indicated variable is not part of the persistent state of the object	
	transient int a;
	
	static int b;
	String name;
	int age;

	// Default constructor
	public SerializePojo(String name, int age, int a, int b) {
		this.name = name;
		this.age = age;
		this.a = a;
		this.b = b;
	}	
	

}
