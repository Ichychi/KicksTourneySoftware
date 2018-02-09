package data;

import main.Settings;

public class Team {
	byte size;//maybe wont need this
	int elo;
	Player[] members;
	
	public Team(Settings s,Player[] m){
		this.size = s.getTeamsize();
		this.members = m;
		for(Player x : m) {
			this.elo = this.elo + x.power;
		}
	}

	public int getElo() {
		return elo;
	}

	public Player[] getMembers() {
		return members;
	}
}
