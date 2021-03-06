package wmticker;

public class Player {
	String name;
	int number;
	Position position;
	int redCard;
	int yellowCard;
	int goals;
	int ownGoals;
	boolean sentOff;

	public Player(String aName, int nr, Position pos) {
		name = aName;
		number = nr;
		position = pos;
		redCard = 0;
		yellowCard = 0;
		ownGoals = 0;
		sentOff = false;
	}

	void setPosition(Position newPosition) {
		position = newPosition;
	}

	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(number).append(" ").append(name).append(" (")
				.append(position).append(")");
		if (goals > 0) {
			report.append(" ").append(goals).append(" goals");
		}
		if (ownGoals > 0) {
			report.append(" ").append(ownGoals).append(" own goals");
		}
		if (yellowCard > 0) {
			report.append(" Y");
		}
		if (redCard > 0 || yellowCard == 2) {
			sentOff = true;
			report.append(" S");
		}
		return report.toString();
	}

}
