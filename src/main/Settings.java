package main;

public class Settings {
	byte teamsize;//range 1-5(4+1Backup)
	byte style;//range 1-3? for the different tourney styles
	byte system;//range 1-3? for the different tourney systems
	//dont have to check for any mismatched input since it will be dropdown menus
	public byte getTeamsize() {
		return teamsize;
	}
	public void setTeamsize(byte teamsize) {
		this.teamsize = teamsize;
	}
	public byte getStyle() {
		return style;
	}
	public void setStyle(byte style) {
		this.style = style;
	}
	public byte getSystem() {
		return system;
	}
	public void setSystem(byte system) {
		this.system = system;
	}
}
