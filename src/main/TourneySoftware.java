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
	    	   createTeams(settings);//TODO Fix balance
	    	   for(int i = 0; i<Teams.size();i++) {
	    		   System.out.println("Team "+(i+1)+"("+Teams.get(i).getElo()+" ELO):");
	    		   for(int j = 0; j<(int)settings.teamsize;j++)
	    			   System.out.println(Teams.get(i).getMembers().get(j).getName());
	    	   }      
	       }
	       
	       public static void createTeams(Settings s) {
	    	   //creating imbalanced teams atm, cuz im just adding them one after the other sorted by "strength"
	    	   Player next;
	    	   int x = 0;
	    	   while(!DFs.isEmpty()) {
	    		   if(x>teamAmount-1)
	    			   x=0;
	    		   next = strongest(DFs);
	    		   if(next != null) {
	    			   Teams.get(x).addPlayer(next);
	    			   DFs.remove(next);
	    			   x++;
	    		   }
	    	   }
	    	   while(!MFs.isEmpty()) {
	    		   if(x>teamAmount-1)
	    			   x=0;
	    		   next = strongest(MFs);
	    		   if(next != null) {
	    			   Teams.get(x).addPlayer(next);
	    			   MFs.remove(next);
	    			   x++;
	    		   }
	    	   }
	    	   while(!FWs.isEmpty()) {
	    		   if(x>teamAmount-1)
	    			   x=0;
	    		   next = strongest(FWs);
	    		   if(next != null) {
	    			   Teams.get(x).addPlayer(next);
	    			   FWs.remove(next);
	    			   x++;
	    		   }
	    	   }
	       }
	       
	       public static Player strongest(List<Player> players) {
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
	       
}
