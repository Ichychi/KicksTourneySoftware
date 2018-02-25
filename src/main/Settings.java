package main;

public class Settings {
	private byte teamsize;//range 1-5(4+1Backup)
	private byte style;//range 1-3? for the different tourney styles
	private byte system;//range 1-3? for the different tourney systems
	//dont have to check for any mismatched input since it will be dropdown menus
	public byte getTeamsize() {
		return teamsize;
	}
	public void setTeamsize(byte teamsize) {
		if (teamsize >= 0) {
			this.teamsize = teamsize;
		}
	}
	public byte getStyle() {
		return style;
	}
	public void setStyle(byte style) {
		if (style >= 0) {
			this.style = style;
		}
	}
	public byte getSystem() {
		return system;
	}
	public void setSystem(byte system) {
		if (system >= 0) {
			this.system = system;
		}
	}
}
