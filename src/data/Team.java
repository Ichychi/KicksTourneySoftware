package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.Settings;

public class Team {
	private byte teamID;
	private byte size;
	private int elo;
	private List<Player> members;
	private byte current;
	private int points;
	
	public Team(Settings s){
		this.size = s.getTeamsize();
		this.members = new ArrayList<>();
		this.current = 0;
		this.elo = 0;
		this.points = 0;
	}

	public void addPlayer(Player p) {
		if(current < size) {
			members.add(p);
			current ++;
			elo += p.getPower();
		}	
		else
			System.out.println("Reserve Players: "+p.getName());
	}
	
	public void sort() {
		Collections.sort(members);
	}
	
	public int getElo() {
		return elo;
	}

	public List<Player> getMembers() {
		return Collections.unmodifiableList(members);
	}

	@Override
	public String toString() {
		return members.toString() + " ";
	}

	public byte getTeamID() {
		return teamID;
	}

	public void setTeamID(byte teamID) {
		this.teamID = teamID;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints(int points) {
		this.points = this.points + points;
	}

}
