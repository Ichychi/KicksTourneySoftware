package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import data.Player;
import data.Team;

public class TourneySoftware {
	private static List<Player> DFs = new ArrayList<>();
    private static List<Player> MFs = new ArrayList<>();
    private static List<Player> FWs = new ArrayList<>();
    private static List<Team> Teams = new ArrayList<>();
    private static int teamAmount = 0;
    
	       public static void main (String[] args) {
	    	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    	    List<Player> playerList = null;
	    	    try { 
	    	    	SAXParser saxParser = saxParserFactory.newSAXParser();
	    	        ParsePlayersHandler handler = new ParsePlayersHandler();
	    	        saxParser.parse(new File("src/data/Participants.xml"), handler);
	    	        playerList = handler.getplayerList();
	    	        for(Player p : playerList) {
	    	        	switch(p.getRole()){
	    	        	case "DF": 
	    	        		DFs.add(p);
	    	        		break;
	    	        	case "MF":
	    	        		MFs.add(p);
	    	        		break;
	    	        	case "FW":
	    	        		FWs.add(p);
	    	        		break;
	    	        	}
	    	        }

	    	   } catch (Exception e) {
	    	       e.printStackTrace();  
	    	   }
	    	   Settings settings = new Settings();
	    	   settings.setTeamsize((byte) 4);
	    	   settings.setStyle((byte) 1);
	    	   settings.setSystem((byte) 1);
	    	   teamAmount = (int) Math.floor(playerList.size()/(int)settings.getTeamsize());
	    	   for(int i=0; i<teamAmount; i++) {
	    		   Teams.add(new Team(settings));
	    	   }
	    	   createTeams(settings);
	    	   sortTeams(Teams);
	    	   for(int i = 0; i<Teams.size();i++) {
	    		   System.out.println("Team "+(i+1)+"("+Teams.get(i).getElo()+" ELO):");
	    		   for(int j = 0; j<(int)settings.teamsize;j++)
	    			   System.out.println(Teams.get(i).getMembers().get(j).getName());
	    	   }      
	       }
	       
	       public static void createTeams(Settings s) {
	    	   Player next;
	    	   for(int i =0;i<teamAmount;i++){
	    		   if(DFs.isEmpty()) {
	    			   System.out.println("Not enough Defenders for "+teamAmount+" Teams");
	    			   System.exit(0);
	    		   }
	    		   next = strongestPlayer(DFs);
	    		   if(next != null) {
	    			   Teams.get(i).addPlayer(next);
	    			   DFs.remove(next);
	    		   }
	    	   }    	  
	    	   for(int i =0;i<teamAmount;i++){
	    		   if(FWs.isEmpty()) {
	    			   System.out.println("Not enough Forwards for "+teamAmount+" Teams");
	    			   System.exit(0);
    		       }
	    		   next = strongestPlayer(FWs);
	    		   if(next != null) {
	    			   weakestTeam(Teams).addPlayer(next);
	    			   FWs.remove(next);
	    		   }
	    	   }
	    	   for(int i =0;i<teamAmount;i++){
	    		   if(MFs.isEmpty()) {
	    			   System.out.println("Not enough Midfielders for "+teamAmount+" Teams");
	    			   System.exit(0);
	    		   }
	    		   next = strongestPlayer(MFs);
	    		   if(next != null) {
	    			   weakestTeam(Teams).addPlayer(next);
	    			   MFs.remove(next);
	    		   }
	    	   }
	    	   List<Player> rest = new ArrayList<>();
	    	   rest.addAll(DFs);
	    	   rest.addAll(MFs);
	    	   rest.addAll(FWs);
	    	   while(!rest.isEmpty()) {	   
	    			   weakestTeam(Teams).addPlayer(strongestPlayer(rest));
	    			   rest.remove(strongestPlayer(rest));
	    	   }
	       }
	       
	       public static void sortTeams(List<Team> teams) {
	    	   for(Team x : teams) {
	    		   x.sort();
	    	   }    	   
	       }
	       
	       public static Player strongestPlayer(List<Player> players) {
	    	   if(!players.isEmpty()) {
	    		   Player strongest = players.get(0);
	    		   for(int i=1 ; i<players.size() ; i++) {
	    			   if (strongest.getPower()<players.get(i).getPower())
	    				   strongest = players.get(i) ;
	    		   }
	    		   return strongest;
	    	   }
			return null;
	       }
	       
	       public static Team weakestTeam(List<Team> teams) {
	    	   if(!teams.isEmpty()) {
	    		   Team weakest = teams.get(0);
	    		   for(Team x : teams) {
	    			   if(x.getElo()<weakest.getElo())
	    				   weakest = x;
	    		   }
	    		   return weakest;
	    	   }
			return null;    	   
	       }
	       
}
