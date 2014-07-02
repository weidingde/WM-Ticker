package wmticker;

public class TickerEvent {
	Match match;
	int time;
	Team team;
	Player player;
	EventType type;

	TickerEvent(Match whichMatch, int when, Team whichTeam, Player who,
			EventType what) {

		match = whichMatch;
		time = when;
		team = whichTeam;
		player = who;
		type = what;
	}

	public String toString() {
		StringBuilder report = new StringBuilder();
		report.append(time).append("'. ").append(type);
		if (player != null) {
			report.append(" ").append(player.name).append(" (")
					.append(team.name).append(", ").append(player.number)
					.append(")");
		}
		return report.toString();
	}
}
