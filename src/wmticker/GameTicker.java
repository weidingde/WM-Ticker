package wmticker;

import java.util.Random;

public class GameTicker {

	public TickerEvent generateEvent(int time, Match m) {
		EventType event = this.randomEvent();
		Team aTeam = this.randomTeam(m);
		Player player = this.randomPlayer(aTeam);

		if (event == EventType.FINISHED || event == EventType.HALFTIME
				|| event == EventType.KICK_OFF) {
			// these 3 event will not be generated randomly(return false when
			// bei validateEvent)
			return new TickerEvent(m, time, null, null, event);
		} else if (event == EventType.YELLOW_RED_CARD && player.yellowCard == 1) {
			// a player will be sent off with red-yellow card
			return new TickerEvent(m, time, aTeam, player, event);
		} else {
			return new TickerEvent(m, time, aTeam, randomPlayer(aTeam), event);
		}
	}

	// get a random team
	private Team randomTeam(Match m) {
		boolean team = new Random().nextBoolean();
		if (team) {
			return m.firstTeam;
		} else
			return m.secondTeam;
	}

	// get a random player
	private Player randomPlayer(Team team) {
		int number = new Random().nextInt(10);
		return team.players.get(number);
	}

	// get a random ticker event
	private EventType randomEvent() {
		int event = new Random().nextInt(12);
		EventType[] events = EventType.values();
		return events[event];
	}
}
