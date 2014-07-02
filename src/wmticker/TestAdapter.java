package wmticker;

import java.util.Random;
import java.util.Vector;

import wmticker.test.TestHarness;

public class TestAdapter implements TestHarness {

	@Override
	public Player createPlayer(String name, int nr, Position pos) {

		Player player = new Player(name, nr, pos);
		return player;
	}

	@Override
	public void changePosition(Player p, Position targetPosition) {

		p.setPosition(targetPosition);

	}

	@Override
	public Team createTeam(String name, Vector<Player> players) {

		Team team = new Team(name, players);
		return team;
	}

	@Override
	public Vector<Player> members(Team t) {

		return t.players;
	}

	@Override
	public TickerEvent createEvent(Match whichMatch, int when, Team whichTeam,
			Player who, EventType what) {

		TickerEvent event = new TickerEvent(whichMatch, when, whichTeam, who,
				what);
		return event;
	}

	@Override
	public Match createMatch(Team firstTeam, Team secondTeam) {

		Match match = new Match(firstTeam, secondTeam);
		return match;
	}

	@Override
	public GameTicker createGameTicker() {
		GameTicker gameTicker = new GameTicker();
		return gameTicker;
	}

	@Override
	public TickerEvent generateRandomEvent(Match m, GameTicker t,
			int currentTime) {
		TickerEvent event;
		do {
			event = t.generateEvent(currentTime, m);
		} while (!validateEvent(m, t, event));
		return event;
	}

	@Override
	public void processEvent(Match m, TickerEvent event) {
		if (event.type == EventType.GOAL) {
			m.goal(event.team);
			event.player.goals++;
		} else if (event.type == EventType.OWN_GOAL) {
			m.ownGoal(event.team);
			event.player.ownGoals++;
		} else if (event.type == EventType.YELLOW_CARD) {
			event.player.yellowCard++;
		} else if (event.type == EventType.YELLOW_RED_CARD
				|| event.type == EventType.RED_CARD) {
			event.player.sentOff = true;
		}

	}

	@Override
	public Vector<TickerEvent> runTicker(Match match, GameTicker t,
			int intensity) {
		Random time = new Random();
		int gameTime = 0;// current game time
		TickerEvent aEvent;// a random event
		Vector<TickerEvent> wholeGame = new Vector<>();
		boolean halfTime = false;// whether the game comes to second half

		// match starts at 0'
		wholeGame
				.add(new TickerEvent(match, 0, null, null, EventType.KICK_OFF));
		while (gameTime < 90) {
			gameTime += time.nextInt(intensity);
			// check for Halbzeitpfiff
			if (gameTime >= 45 && !halfTime) {
				gameTime = 45;
				halfTime = true;
				wholeGame.add(new TickerEvent(match, 45, null, null,
						EventType.HALFTIME));
				wholeGame.add(new TickerEvent(match, 45, null, null,
						EventType.KICK_OFF));
			}
			// check for Abpfiff
			else if (gameTime >= 90 && halfTime) {
				gameTime = 90;
				wholeGame.add(new TickerEvent(match, 90, null, null,
						EventType.FINISHED));
			}
			// generate an event
			else {
				aEvent = this.generateRandomEvent(match, t, gameTime);
				this.processEvent(match, aEvent);
				wholeGame.add(aEvent);
				// if it is a goal, then add a KICKOFF event
				if (aEvent.type == EventType.GOAL
						|| aEvent.type == EventType.OWN_GOAL) {
					wholeGame.add(new TickerEvent(match, gameTime, null, null,
							EventType.KICK_OFF));
				}
			}
		}
		return wholeGame;
	}

	@Override
	public boolean validateEvent(Match match, GameTicker t, TickerEvent event) {
		if (!event.player.sentOff) {
			if (event.type == EventType.YELLOW_CARD
					&& event.player.yellowCard == 0) {
				return true;
			} else if (event.type == EventType.YELLOW_RED_CARD
					&& event.player.yellowCard == 1) {
				return true;
			} else if (event.type == EventType.RED_CARD) {
				return true;
			} else
				return true;
		} else
			return false;
	}

}
