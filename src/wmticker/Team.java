package wmticker;

import java.util.Vector;

public class Team {
	String name;
	Vector<Player> players;
	int goal;

	Team(String aName, Vector<Player> aPlayers) {
		name = aName;
		players = aPlayers;
	}

	Vector<Player> getPlayers() {
		return players;
	}

	public String toString() {
		StringBuilder nameList = new StringBuilder("Team: ");
		nameList.append(name).append("\n");
		for (Player a : players) {
			nameList.append("  ").append(a.number).append(" ").append(a.name)
					.append(" (").append(a.position).append(")").append("\n");
		}
		return nameList.toString();
	}

}
