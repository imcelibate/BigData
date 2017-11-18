package org.javaUtils;

import org.javaUtils.serialize.DeSerializer;
import org.javaUtils.serialize.Serializer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Serializer.serialize();
        System.out.println("------AMMA GURU------------");
        DeSerializer.deSerialize();
    }
}
