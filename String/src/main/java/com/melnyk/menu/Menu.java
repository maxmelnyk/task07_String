package com.melnyk.menu;

import com.melnyk.Printable;
import com.melnyk.StringUtils;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

  private Map<String, String> menu;
  private Map<String, Printable> commands;

  private Locale locale;
  private ResourceBundle resourceBundle;

  private void setMenu() {
    menu = new LinkedHashMap<>();

    menu.put("1", resourceBundle.getString("1"));
    menu.put("2", resourceBundle.getString("2"));
    menu.put("3", resourceBundle.getString("3"));
    menu.put("4", resourceBundle.getString("4"));
    menu.put("5", resourceBundle.getString("5"));
    menu.put("6", resourceBundle.getString("6"));
    menu.put("Q", resourceBundle.getString("Q"));
  }

  public void showMenu() {
    Scanner key = new Scanner(System.in);
    String keyMenu;

    locale = new Locale("en");
    resourceBundle = ResourceBundle.getBundle("menu", locale);

    setMenu();

    commands = new LinkedHashMap<>();
    commands.put("1", this::getStringUtils);
    commands.put("2", this::getUkrainianMenu);
    commands.put("3", this::getEnglishMenu);
    commands.put("4", this::getRegularExpression);
    commands.put("5", this::getSplittedString);
    commands.put("6", this::getReplaceAllTheVowels);

    for (; ; ) {
      outputMenu();
      System.out.print("Select key: ");
      keyMenu = key.nextLine();
      if (keyMenu.equals("Q")) {
        break;
      }
      try {
        commands.get(keyMenu).print();
      } catch (Exception ignore) {
      }
    }
  }

  private void getStringUtils() {
    System.out.println();
    StringUtils stringUtils = new StringUtils();
    stringUtils.addToParameters("Hello")
        .addToParameters("my numbers is")
        .addToParameters(142);
    System.out.println(stringUtils.concat());
  }

  private void getUkrainianMenu() {
    locale = new Locale("ua");
    resourceBundle = ResourceBundle.getBundle("menu", locale);
    setMenu();

  }

  private void getEnglishMenu() {
    locale = new Locale("en");
    resourceBundle = ResourceBundle.getBundle("menu", locale);
    setMenu();
  }

  private void getRegularExpression() {
    System.out.println("\nMy sentence:");
    String message = "Hello world.";
    System.out.println(message);
    System.out.println("Result of check a sentence to see that it begins with a capital letter and ends with a period:\n"
        + message.matches("[A-Z]+ *(\\w+ *)*\\w*\\.+"));

    //The same task, but with Pattern and Matcher
    Pattern p = Pattern.compile("[A-Z]+ *(\\w+ *)*\\w*\\.+");
    Matcher m = p.matcher(message);
    System.out.println(m.matches());
  }

  private void getSplittedString() {
    System.out.println("\nMy string:");
    String string = "This is my sentence.";
    System.out.println(string);
    System.out.println("After split:");
    String[] strings = string.split(" ");
    for (String s : strings) {
      System.out.println(s);
    }
  }

  private void getReplaceAllTheVowels() {
    System.out.println("\nMy text:");
    String text = "Internationalization is also known as I18N";
    System.out.println(text);
    String[] vowels = {"A", "E", "I", "O", "U", "a", "e", "i", "o", "u"};
    String textAfterReplace = text;
    for (String vowel : vowels){
      textAfterReplace = textAfterReplace.replaceAll(vowel, "_");
    }
    System.out.println(textAfterReplace);
  }

  private void outputMenu() {
    System.out.println();
    for (String key : menu.keySet()) {
      System.out.println(menu.get(key));
    }
  }
}
