package com.melnyk;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

  private List<Object> objects = new ArrayList<Object>();

  public StringUtils addToParameters(Object param) {
      objects.add(param);
    return this;
  }

  public String concat() {
    StringBuilder sb = new StringBuilder();
    for (Object param : objects) {
      sb.append(param).append(" ");
    }
    return sb.toString();
  }
}
