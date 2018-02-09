package main;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.Player;
import data.Team;

public class TourneySoftware {
	
	       public static void main (String[] args) {
	    	   Settings settings = new Settings();
	    	   settings.setTeamsize((byte) 4);
	    	   settings.setStyle((byte) 1);
	    	   settings.setSystem((byte) 1);
	    	   Player[] players = new Player[2];//make this dynamic
	    	   Scanner reader = new Scanner(System.in);
	    	   for(int i=0; i<players.length; i++) {
	    		   players[i] = new Player();
	    		   createPlayer(players[i],reader);
	    	   }
	    	   Team test = new Team(settings, players);
	    	   System.out.println("Team ELO: "+ test.getElo());
	    	   Team[] teams = new Team[1];//make this dynamic
	    	   for(int i=0; i<(players.length/settings.getTeamsize())+1; i++) {
	    		   teams[i] = new Team(settings, players);
	    		   createTeam();//WORK IN PROGRESS
	    	   }
	    	   
	    	   
	    	   //the following for loop is just for testing
	    	   for(int i=0; i<players.length; i++) {
	    	   for (Field field : players[i].getClass().getDeclaredFields()) {
	    		    field.setAccessible(true);
	    		    String name = field.getName();
	    		    Object value;
					try {
						value = field.get(players[i]);
						 System.out.printf("%s: %s%n", name, value);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    	   }
	           reader.close();
	       }
	       
	       public static void createTeam() {
	    	   
	       }
	       
	       public static void createPlayer(Player p, Scanner s) {
	    	   p.setName(name(s));
	    	   p.setPosition(position(s));
	    	   p.setLevel(level(s));//only need to test for nonvalid input here since everything is else will be dropdown or a name
	    	   p.setRating(rating(s)); 
	    	   p.calculatePower();
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
	    	   while(true) {
	    	   try {
	    		   byte x = s.nextByte();
	    		   if(1<=x && x<= 60)
	    		   return x;
	    	   }
	    	   catch(InputMismatchException e) {
	    		   String error = s.next();
	    		   System.out.println(error+" is not a valid input.\n"+"Enter a level(1-60)!");
	    	   }
	    	   }
	       }
	       public static Byte rating (Scanner s) {
	    	   System.out.println("Enter the characters rating:");
	    	   return s.nextByte();
	       }
}
