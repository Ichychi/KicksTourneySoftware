package data;

public class Player {
	String name;
	String position;
	byte level;
	byte rating;
	int power;
	
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
		this.power = level+5*rating;
	}

}
