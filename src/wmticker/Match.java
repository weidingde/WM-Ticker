package wmticker;

public class Match {
	Team firstTeam;
	Team secondTeam;

	Match(Team a, Team b) {

		firstTeam = a;
		secondTeam = b;

	}

	void goal(Team a) {
		if (a.equals(firstTeam)) {
			firstTeam.goal++;
		} else if (a.equals(secondTeam)) {
			secondTeam.goal++;
		}
	}

	void ownGoal(Team a) {
		if (a.equals(firstTeam)) {
			secondTeam.goal++;
		} else if (a.equals(secondTeam)) {
			firstTeam.goal++;
		}
	}

	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(firstTeam.name).append(" : ").append(secondTeam.name)
				.append(" => ").append(firstTeam.goal).append(":")
				.append(secondTeam.goal);
		return report.toString();
	}
}
