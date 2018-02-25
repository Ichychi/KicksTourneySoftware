package main;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import data.Player;
import data.Team;
import utils.Utils;

public class TourneySoftware {

	public static void main(String[] args) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<Player> playerList = null;
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			ParsePlayersHandler handler = new ParsePlayersHandler();
			saxParser.parse(new File("src/data/Participants.xml"), handler);
			playerList = handler.getplayerList();
			for (Player p : playerList) {
				switch (p.getRole()) {
				case "DF":
					Utils.addDF(p);
					break;
				case "MF":
					Utils.addMF(p);
					break;
				case "FW":
					Utils.addFW(p);
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
		Utils.setTeamAmount((int) Math.floor(playerList.size() / (int) settings.getTeamsize()));
		for (int i = 0; i < Utils.getTeamAmount(); i++) {
			Utils.addTeam(new Team(settings));
		}
		Utils.createTeams(settings);
		Utils.sortTeams();
		Utils.printTeams(settings);
		Utils.createGroups();
	}
}
