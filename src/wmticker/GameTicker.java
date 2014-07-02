package wmticker;

import java.util.Random;

public class GameTicker {

	public TickerEvent generateEvent(int time, Match m) {
		EventType event = this.randomEvent();
		if (event == EventType.FINISHED || event == EventType.HALFTIME
				|| event == EventType.KICK_OFF) {
			return new TickerEvent(m, time, null, null, event);
		} else {
			Team aTeam = this.randomTeam(m);
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
