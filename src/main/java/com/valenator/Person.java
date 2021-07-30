package com.valenator;

import com.valenator.anotate.Init;
import com.valenator.anotate.JsonElement;
import com.valenator.anotate.JsonSerializable;

@JsonSerializable
public class Person {

  @JsonElement
  private String firstName;

  @JsonElement
  private String lastName;

  @JsonElement(key = "personAge")
  private String age;

  private String address;

  public Person(String firstName, String lastName, String age, String address) {
    this.firstName = firstName;
    this.lastName  = lastName;
    this.age       = age;
    this.address   = address;
  }

  @Init
  private void initNames() {
    this.firstName = this.firstName.substring(0, 1).toUpperCase()
                   + this.firstName.substring(1).toLowerCase();

    this.lastName  = this.lastName.substring(0, 1).toUpperCase()
                   + this.lastName.substring(1).toLowerCase();
  }

  // Standard getters and setters
}