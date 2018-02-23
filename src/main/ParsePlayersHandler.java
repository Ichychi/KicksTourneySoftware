package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import data.Player;

public class ParsePlayersHandler extends DefaultHandler {
    private List<Player> playerList = null;
    private Player player = null;
    
    public List<Player> getplayerList() {
//        return playerList;
        return Collections.unmodifiableList(playerList);
    }

    boolean bPos = false;
    boolean bLev = false;
    boolean bRat = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
      if (qName.equalsIgnoreCase("player")) {
    	  String name = attributes.getValue("name");
          player = new Player();
          player.setName(name);
          if (playerList == null)
        	  playerList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("position")) {
            bPos = true;
        } else if (qName.equalsIgnoreCase("level")) {
            bLev = true;
        } else if (qName.equalsIgnoreCase("rating")) {
            bRat = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("player")) {
        	player.calculatePower();
        	player.setRole();
            playerList.add(player);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bLev) {
            player.setLevel((byte) Integer.parseInt(new String(ch, start, length)));
            bLev = false;
        } else if (bPos) {
            player.setPosition(new String(ch, start, length));
            bPos = false;
        } else if (bRat) {
            player.setRating((byte) Integer.parseInt(new String(ch, start, length)));
            bRat = false;
        }
    }
}
