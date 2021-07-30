package com.valenator;

import com.valenator.anotate.Init;
import com.valenator.anotate.JsonElement;
import com.valenator.anotate.JsonSerializable;
import com.valenator.anotate.JsonSerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJsonConverter {
  public String convertToJson(Object object) throws JsonSerializationException {
    try {
      checkIfSerializable(object);
      initializeObject(object);
      return getJsonString(object);
    } catch (Exception e) {
      throw new JsonSerializationException(e.getMessage());
    }
  }

  private void checkIfSerializable(Object object) throws JsonSerializationException {
    if (Objects.isNull(object)) {
      throw new JsonSerializationException("The object to serialize is null");
    }

    Class<?> clazz = object.getClass();
    if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
      throw new JsonSerializationException("The class " + clazz.getSimpleName() + " is not annotated with JsonSerializable");
    }
  }

  private void initializeObject(Object object) throws Exception {
    Class<?> clazz = object.getClass();
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(Init.class)) {
        method.setAccessible(true);
        method.invoke(object);
      }
    }
  }

  private String getJsonString(Object object) throws Exception {
    Class<?>            clazz           = object.getClass();
    Map<String, String> jsonElementsMap = new HashMap<>();
    for (Field field : clazz.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(JsonElement.class)) {
        JsonElement element = field.getAnnotation(JsonElement.class);
        String      key     = element.key().isEmpty() ? field.getName() : element.key();
        jsonElementsMap.put(key, (String) field.get(object));
      }
    }



    String jsonString = jsonElementsMap.entrySet()
                                       .stream()
                                       .map(entry -> String.format("\"%s\": \"%s\"", entry.getKey(),  entry.getValue()))
                                       .collect(Collectors.joining(", "));
    return "{" + jsonString + "}";
  }
}