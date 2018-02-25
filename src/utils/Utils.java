package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import data.Player;
import data.Team;
import main.Settings;

public abstract class Utils {
	
	private static List<Player> DFs = new ArrayList<>();
	private static List<Player> MFs = new ArrayList<>();
	private static List<Player> FWs = new ArrayList<>();
	private static List<Team> teams = new ArrayList<>();
	private static int teamAmount = 0;
	private static LinkedHashMap<String, ArrayList<Team>> groups = new LinkedHashMap<>();
	
	public static int getTeamAmount() {
		return teamAmount;
	}
	
	public static void setTeamAmount(int teamAmount) {
		if (teamAmount > 0) {
			Utils.teamAmount = teamAmount;
		}
	}
	
	public static List<Player> getDFs() {
		return Collections.unmodifiableList(DFs);
	}
	
	public static List<Player> getMFs() {
		return Collections.unmodifiableList(MFs);
	}
	
	public static List<Player> getFWs() {
		return Collections.unmodifiableList(FWs);
	}
	
	public static void addDF(Player p) {
		if (p != null) {
			DFs.add(p);
		}
	}
	
	public static void addMF(Player p) {
		if (p != null) {
			MFs.add(p);
		}
	}
	
	public static void addFW(Player p) {
		if (p != null) {
			FWs.add(p);
		}
	}
	
	public static List<Team> getTeams() {
		return Collections.unmodifiableList(teams);
	}
	
	public static void addTeam(Team t) {
		if (t != null) {
			teams.add(t);
		}
	}

	public static void createGroups() {
		int numberOfTeams = teams.size();
		int numberOfGroups = numberOfTeams / 4;
		int teamsInGroup = numberOfTeams / numberOfGroups;
		for (int i = 0; i < numberOfGroups; i++) {
			groups.put("Group " + (i + 1), new ArrayList<>());
		}
		List<Team> teamsCopy = new ArrayList<>(teams);
		while (teamsCopy.size() > 0) {
			int index = new Random().nextInt(teamsCopy.size());
			Team t = teamsCopy.get(index);
			int randomGroup = new Random().nextInt(numberOfGroups);
			if (teamsCopy.size() == 1 && numberOfTeams % 2 == 1) { //check if the amount of the teams participating in the tournament is an odd number
				//if it's true we add the last existing team without group to a random one and stop the group creator.
				groups.get("Group " + (randomGroup + 1)).add(t);
				teamsCopy.remove(index);
				break;
			}
			if (groups.get("Group " + (randomGroup + 1)).size() != teamsInGroup) { //we add the team to a random group
				groups.get("Group " + (randomGroup + 1)).add(t);
				teamsCopy.remove(index);
			}
		}
		printGroups(groups);
	}
	
	private static void printGroups(LinkedHashMap<String, ArrayList<Team>> prGroups) {
		System.out.println("GROUPS: ");
		for (Entry<String, ArrayList<Team>> entry : prGroups.entrySet()) {
			System.out.println(entry.getKey());
			ArrayList<Team> teamsToPrint = entry.getValue();
			for (int i = 0; i < teamsToPrint.size(); i++) {
				System.out.println("Team " + (i + 1) + teamsToPrint.get(i));
			}
			System.out.println();
		}
	}
	
	public static void generateFixures() {
		LinkedHashMap<String, ArrayList<String>> fixtures = new LinkedHashMap<>();
		System.out.println("FIXTURES:");
		for (Entry<String, ArrayList<Team>> entry : groups.entrySet()) {
			System.out.println(entry.getKey() + " fixtures: (HOME / AWAY)");
			ArrayList<Team> teamsToPrint = entry.getValue();
			for (int i = 0; i < teamsToPrint.size(); i++) {
				for (int j = i+1; j < teamsToPrint.size(); j++) {
					System.out.println("TEAM " + (i+1) + " vs TEAM " + (j+1) + " / TEAM " + (j+1) + " vs TEAM" + (i+1));
				}
			}
			System.out.println();
		}
	}
	
	public static void sortTeams() {
		for (Team x : teams) {
			x.sort();
		}
	}
	
	public static void createTeams(Settings s) {
		Player next;
		for (int i = 0; i < teamAmount; i++) {
			if (DFs.isEmpty()) {
				System.out.println("Not enough Defenders for " + teamAmount + " Teams");
				return;
			}
			next = strongestPlayer(DFs);
			if (next != null) {
				teams.get(i).addPlayer(next);
				DFs.remove(next);
			}
		}
		for (int i = 0; i < teamAmount; i++) {
			if (FWs.isEmpty()) {
				System.out.println("Not enough Forwards for " + teamAmount + " Teams");
				return;
			}
			next = strongestPlayer(FWs);
			if (next != null) {
				weakestTeam(teams).addPlayer(next);
				FWs.remove(next);
			}
		}
		for (int i = 0; i < teamAmount; i++) {
			if (MFs.isEmpty()) {
				System.out.println("Not enough Midfielders for " + teamAmount + " Teams");
				return;
			}
			next = strongestPlayer(MFs);
			if (next != null) {
				weakestTeam(teams).addPlayer(next);
				MFs.remove(next);
			}
		}
		List<Player> rest = new ArrayList<>();
		rest.addAll(DFs);
		rest.addAll(MFs);
		rest.addAll(FWs);
		while (!rest.isEmpty()) {
			weakestTeam(teams).addPlayer(strongestPlayer(rest));
			rest.remove(strongestPlayer(rest));
		}
	}
	
	public static Player strongestPlayer(List<Player> players) {
		if (!players.isEmpty()) {
			Player strongest = players.get(0);
			for (int i = 1; i < players.size(); i++) {
				if (strongest.getPower() < players.get(i).getPower())
					strongest = players.get(i);
			}
			return strongest;
		}
		return null;
	}
	
	public static Team weakestTeam(List<Team> teams) {
		if (!teams.isEmpty()) {
			Team weakest = teams.get(0);
			for (Team x : teams) {
				if (x.getElo() < weakest.getElo())
					weakest = x;
			}
			return weakest;
		}
		return null;
	}
	
	public static void printTeams(Settings settings) {
		for (int i = 0; i < Utils.getTeams().size(); i++) {
			System.out.println("Team " + (i + 1) + "(" + Utils.getTeams().get(i).getElo() + " ELO):");
			for (int j = 0; j < (int) settings.getTeamsize(); j++) {
				System.out.println(Utils.getTeams().get(i).getMembers().get(j).getName() + " -- "
						+ Utils.getTeams().get(i).getMembers().get(j).getLevel() + "lv -- "
						+ Utils.getTeams().get(i).getMembers().get(j).getPosition());
			}
			System.out.println();
		}
	}
}
