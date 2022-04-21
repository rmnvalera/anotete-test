package com.valenator;

import java.util.ArrayList;
import java.util.List;

public class Main {

  private final static ObjectToJsonConverter objectToJsonConverter = new ObjectToJsonConverter();

  public void run() throws Exception{

    Person person = new Person("oZGeek", "96", "25", "Myra");

    List<String> personData = new ArrayList<>();
    personData.add("firstName");
    personData.add("Valera");

    personData.add("lastName");
    personData.add("Roman");

    personData.add("age");
    personData.add("25");

    personData.add("address");
    personData.add("Niko");


    objectToJsonConverter.convertToObject(personData, Person.class);

    String json = objectToJsonConverter.convertToJson(person);
    System.out.println(json);
  }

  public static void main(String[] args) throws Exception{
    new Main().run();
  }
}
