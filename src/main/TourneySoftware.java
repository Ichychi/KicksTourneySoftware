package main;

import java.lang.reflect.Field;
import java.util.Scanner;

import data.Player;

public class TourneySoftware {

	       public static void main (String[] args) {
	    	   Scanner reader = new Scanner(System.in);
	    	   Player p1 = new Player();
	    	   createPlayer(p1,reader);
	    	   //the following for loop is just for testing
	    	   for (Field field : p1.getClass().getDeclaredFields()) {
	    		    field.setAccessible(true);
	    		    String name = field.getName();
	    		    Object value;
					try {
						value = field.get(p1);
						 System.out.printf("%s: %s%n", name, value);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	           reader.close();
	       }
	       
	       public static void createPlayer(Player p, Scanner r) {
	    	   p.setName(name(r));
	    	   p.setPosition(position(r));
	    	   p.setLevel(level(r));
	    	   p.setRating(rating(r)); 
	       }
	       public static String name (Scanner s) {
	    	   System.out.println("Enter the characters name:");
	    	   return s.next();
	       }
	       public static String position (Scanner s) {
	    	   System.out.println("Enter the characters position:");
	    	   return s.next();
	       }
	       public static Byte level (Scanner s) {
	    	   System.out.println("Enter the characters level:");
	    	   return s.nextByte();
	       }
	       public static Byte rating (Scanner s) {
	    	   System.out.println("Enter the characters rating:");
	    	   return s.nextByte();
	       }
}
