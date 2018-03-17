package data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Group {
	private byte GroupID;
	private List<Team> teams = new ArrayList<>();
	private LinkedHashMap<Integer, Team> standings = new LinkedHashMap<>();//points achieved -> team
	
	public void addTeam(Team team) {
		this.teams.add(team);
		this.standings.put(team.getPoints(), team);
	}

	public byte getId() {
		return GroupID;
	}

	public void setId(byte id) {
		this.GroupID = id;
	}

	public List<Team> getTeams() {
		return teams;
	}
}
