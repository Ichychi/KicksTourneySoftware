package data;
public class Player {
	String name;
	String position;
	byte level;
	byte rating;
	int power;
	String role;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}

	public int getPower() {
		return power;
	}

	public void calculatePower() {
		//going to do a better calculation later on
		this.power = (int) (level+Math.ceil(0.5*(rating/level)));
	}

	public void setRole() {
		String x = this.getPosition();
		if(x.equals("ST")||x.equals("CF")||x.equals("WF"))
			this.role = "FW";
		else if(x.equals("AM")||x.equals("SM")||x.equals("CM")||x.equals("DM"))
			this.role = "MF";
		else if(x.equals("SB")||x.equals("CB")||x.equals("SW"))
			this.role = "DF";
	}
	public String getRole() {
		return role;
	}

}
