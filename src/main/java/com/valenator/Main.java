package com.valenator;

public class Main {

  private final static ObjectToJsonConverter objectToJsonConverter = new ObjectToJsonConverter();

  public void run() throws Exception{

    Person person = new Person("oZGeek", "96", "25", "Myra");

    String json = objectToJsonConverter.convertToJson(person);
    System.out.println(json);
  }

  public static void main(String[] args) throws Exception{
    new Main().run();
  }
}
