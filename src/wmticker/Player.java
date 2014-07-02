package wmticker;

public class Player {
	String name;
	int number;
	Position position;
	int redCard = 0;
	int yellowCard = 0;
	int goals = 0;
	int ownGoals = 0;
	boolean sentOff = false;

	public Player(String aName, int nr, Position pos) {
		name = aName;
		number = nr;
		position = pos;// TODO check if it can be initialized
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
