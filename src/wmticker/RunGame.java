package wmticker;

import java.util.Vector;


public class RunGame {

	public static void main(String[] args) {
		Team spain, netherlands;
		Match match;
		TestAdapter harness;
		GameTicker ticker = new GameTicker();
		harness = new TestAdapter();

		Object[][] spainData = new Object[][] {
				{ "Casillas", 1, Position.GOALKEEPER },

				{ "Azpilicueta", 22, Position.DEFENDER },
				{ "Sergio Ramos", 15, Position.DEFENDER },
				{ "Pique", 3, Position.DEFENDER },
				{ "Jordi Alba", 18, Position.DEFENDER },

				{ "Busquets", 16, Position.MIDFIELDER },
				{ "Xabi Alonso", 14, Position.MIDFIELDER },

				{ "Iniesta", 6, Position.FORWARD },
				{ "Xavi", 8, Position.FORWARD },
				{ "Pedro", 11, Position.FORWARD },
				{ "Diego Costa", 19, Position.FORWARD } };

		Object[][] netherlandsData = new Object[][] {
				{ "Cillessen", 1, Position.GOALKEEPER },

				{ "Janmaat", 7, Position.DEFENDER },
				{ "Vlaar", 2, Position.DEFENDER },
				{ "de Vrij", 3, Position.DEFENDER },

				{ "Martins Indi", 4, Position.MIDFIELDER },
				{ "Blind", 5, Position.MIDFIELDER },
				{ "de Jong", 8, Position.MIDFIELDER },
				{ "de Guzman", 6, Position.MIDFIELDER },

				{ "Sneijder", 10, Position.FORWARD },
				{ "van Persie", 9, Position.FORWARD },
				{ "Robben", 11, Position.FORWARD } };

		// create 2 teams for the match
		Vector<Player> players = new Vector<Player>(netherlandsData.length);
		Player thisPlayer = null;
		for (Object[] entries : spainData) {
			thisPlayer = harness.createPlayer((String) entries[0],
					(Integer) entries[1], (Position) entries[2]);
			players.add(thisPlayer);
		}
		spain = harness.createTeam("Spain", players);

		players.clear();
		thisPlayer = null;
		for (Object[] entries : netherlandsData) {
			thisPlayer = harness.createPlayer((String) entries[0],
					(Integer) entries[1], (Position) entries[2]);
			players.add(thisPlayer);
		}
		netherlands = harness.createTeam("Netherlands", players);

		// creat match
		match = harness.createMatch(spain, netherlands);
		System.out.println(match.toString());
		

		// output game log
		Vector<TickerEvent> events = harness.runTicker(match, ticker, 10);
		for(TickerEvent event:events){
			System.out.println(event.toString());
		}
		//output game result
		System.out.println(match.toString());

	}
}