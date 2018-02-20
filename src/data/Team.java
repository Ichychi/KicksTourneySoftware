package data;

import java.util.ArrayList;
import java.util.List;

import main.Settings;

public class Team {
	byte size;
	int elo;
	List<Player> members;
	byte current;
	
	public Team(Settings s){
		this.size = s.getTeamsize();
		this.members = new ArrayList<>();
		this.current = 0;
		this.elo = 0;
	}

	public void addPlayer(Player p) {
		if(current < size) {
			members.add(p);
			current ++;
		}	
		else
			System.out.println("Reserve Players: "+p.getName());
	}
	
	public int getElo() {
		for(Player x : members) {
			elo += x.power;
		}
		return elo;
	}

	public List<Player> getMembers() {
		return members;
	}


}
