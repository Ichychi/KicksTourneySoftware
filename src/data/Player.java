package data;
public class Player implements Comparable<Player> {
	private String name;
	private String position;
	private byte level;
	private byte rating;
	private int power;
	private String role;
	private int sortValue;
	
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
		this.power = (int) (level*rating+5*rating);
	}

	public void setRole() {
		String x = this.getPosition();
		if(x.equals("ST")||x.equals("CF")||x.equals("WF")) {
			this.role = "FW";
			this.sortValue = 3;
		}
		else if(x.equals("AM")||x.equals("SM")||x.equals("CM")||x.equals("DM")) {
			this.role = "MF";
			this.sortValue = 2;
		}
		else if(x.equals("SB")||x.equals("CB")||x.equals("SW")) {
			this.role = "DF";
			this.sortValue = 1;
		}
	}
	
	public String getRole() {
		return role;
	}

	public int getSortValue() {
		return sortValue;
	}

	@Override
	public int compareTo(Player p) {
		int compareage=((Player)p).getSortValue();
        return this.sortValue-compareage;
	}

}
