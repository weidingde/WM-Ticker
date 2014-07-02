package wmticker.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.framework.JUnit4TestAdapter; // for main
import junit.textui.TestRunner; // for the test runner

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import wmticker.EventType;
import wmticker.GameTicker;
import wmticker.Match;
import wmticker.Player;
import wmticker.Position;
import wmticker.Team;
import wmticker.TestAdapter;
import wmticker.TickerEvent;
// for main
// for the test runner

public class TeamTest {

  private Team spain, netherlands;
  private Match match;
  private TestHarness harness;
  private Player xabiAlonso, vanPersie, robben, deVrij;
  private GameTicker ticker;

  private TickerEvent goal1, goal2, goal3, goal4, goal5, goal6;
  private String eventString1, eventString2, eventString3, eventString4, 
  eventString5, eventString6;
  private Object[][] spainData =
      new Object[][] {
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
      { "Diego Costa", 19, Position.FORWARD }
  };
  private String[] spainString = new String[] {
      "1 Casillas (GOALKEEPER)",
      "22 Azpilicueta (DEFENDER)",
      "15 Sergio Ramos (DEFENDER)",
      "3 Pique (DEFENDER)",
      "18 Jordi Alba (DEFENDER)",
      "16 Busquets (MIDFIELDER)",
      "14 Xabi Alonso (MIDFIELDER)",
      "6 Iniesta (FORWARD)",
      "8 Xavi (FORWARD)",
      "11 Pedro (FORWARD)",
      "19 Diego Costa (FORWARD)"
  };
  private Object[][] netherlandsData = new Object[][] {
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
      { "Robben", 11, Position.FORWARD }
  };
  
  private String[] ndlString = new String[] {
      "1 Cillessen (GOALKEEPER)",
      "7 Janmaat (DEFENDER)", 
      "2 Vlaar (DEFENDER)", 
      "3 de Vrij (DEFENDER)",      
      "4 Martins Indi (MIDFIELDER)", 
      "5 Blind (MIDFIELDER)",
      "8 de Jong (MIDFIELDER)",
      "6 de Guzman (MIDFIELDER)",     
      "10 Sneijder (FORWARD)",
      "9 van Persie (FORWARD)", 
      "11 Robben (FORWARD)"
  };
 
  @Before
  public void setup() {
    harness = new TestAdapter();
    spain = createTeamFromMatrix("Spain", spainData);
    xabiAlonso = harness.members(spain).elementAt(6);
    netherlands = createTeamFromMatrix("Netherlands", netherlandsData);
    match = harness.createMatch(spain, netherlands);
    vanPersie = harness.members(netherlands).elementAt(9);
    robben = harness.members(netherlands).elementAt(10);
    deVrij = harness.members(netherlands).elementAt(3);
    goal1 = harness.createEvent(match, 27, spain, xabiAlonso, EventType.PENALTY_GOAL);
    eventString1 = "27'. PENALTY_GOAL Xabi Alonso (Spain, 14)";
    goal2 = harness.createEvent(match, 44, netherlands, vanPersie, EventType.GOAL);
    eventString2 = "44'. GOAL van Persie (Netherlands, 9)";
    goal3 = harness.createEvent(match, 53, netherlands, robben, EventType.GOAL);
    eventString3 = "53'. GOAL Robben (Netherlands, 11)";
    goal4 = harness.createEvent(match, 64, netherlands, deVrij, EventType.GOAL);
    eventString4 = "64'. GOAL de Vrij (Netherlands, 3)";
    goal5 = harness.createEvent(match, 72, netherlands, vanPersie, EventType.GOAL);
    eventString5 = "72'. GOAL van Persie (Netherlands, 9)";
    goal6 = harness.createEvent(match, 80, netherlands, robben, EventType.GOAL);
    eventString6 = "80'. GOAL Robben (Netherlands, 11)";
    ticker = harness.createGameTicker();
  }
  
  private Team createTeamFromMatrix(String name, Object[][] matrix) {
    Vector<Player> players = new Vector<Player>(matrix.length);
    Player thisPlayer = null;
    for (Object[] entries : matrix) {
      thisPlayer = harness.createPlayer((String) entries[0], (Integer)entries[1],
          (Position)entries[2]);
      players.add(thisPlayer);
    }
    return harness.createTeam(name, players);
  }
  
  @Test
  public void testPlayersSpain() {
    Vector<Player> players = harness.members(spain);
    for (int pos = 0; pos < players.size(); pos++) {
      assertEquals("incorrect player encoding", spainString[pos],
          players.elementAt(pos).toString());
    }
  }
  
  @Test
  public void testPlayersNetherlands() {
    Vector<Player> players = harness.members(netherlands);
    for (int pos = 0; pos < players.size(); pos++) {
      assertEquals("incorrect player encoding", ndlString[pos],
          players.elementAt(pos).toString());
    }
  }

  @Test
  public void testTeamSpain() {
    StringBuffer sb = new StringBuffer(500);
    sb.append("Team: Spain\n");
    for (String s : spainString) {
      sb.append("  ").append(s).append("\n");
    }
    assertEquals("String encoding of Spain incorrect", sb.toString(), spain.toString());
  }
  
  @Test
  public void testTeamNL() {
    StringBuffer sb = new StringBuffer(500);
    sb.append("Team: Netherlands\n");
    for (String s : ndlString) {
      sb.append("  ").append(s).append("\n");
    }
    assertEquals("String encoding of Netherlands incorrect", 
        sb.toString(), netherlands.toString());
  }

  @Test
  public void testChangePosition() {
    // "1 Casillas (GOALKEEPER)"
    Player p = harness.members(spain).elementAt(0);
    // should now be "1 Casillas (DEFENDER)"
    harness.changePosition(p, Position.DEFENDER);
    assertEquals("position change incorrect", "1 Casillas (DEFENDER)", p.toString());

    // should now be "1 Casillas (MIDFIELDER)"
    harness.changePosition(p, Position.MIDFIELDER);
    assertEquals("position change incorrect", "1 Casillas (MIDFIELDER)", p.toString());

    // should now be "1 Casillas (FORWARD)"
    harness.changePosition(p, Position.FORWARD);
    assertEquals("position change incorrect", "1 Casillas (FORWARD)", p.toString());

    // should now be "1 Casillas (SUBSTITUTE)"
    harness.changePosition(p, Position.SUBSTITUTE);
    assertEquals("position change incorrect", "1 Casillas (SUBSTITUTE)", p.toString());
  }
  
  @Test
  public void testTickerEventsToString() {
    assertEquals("incorrect TickerEvent.toString()", eventString1, goal1.toString());
    assertEquals("incorrect TickerEvent.toString()", eventString2, goal2.toString());
    assertEquals("incorrect TickerEvent.toString()", eventString3, goal3.toString());
    assertEquals("incorrect TickerEvent.toString()", eventString4, goal4.toString());
    assertEquals("incorrect TickerEvent.toString()", eventString5, goal5.toString());
    assertEquals("incorrect TickerEvent.toString()", eventString6, goal6.toString());
  }
  
  @Test
  public void validateActualEvents() {
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal1));
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal2));
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal3));
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal4));
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal5));
    assertTrue("TickerEvent should have been OK",
        harness.validateEvent(match, ticker, goal6));
  }
  
  /*
   * to be done:
   * - validate illegal events
   * - test TickerEvent generateRandomEvent(Match, GameTicker, int);
   * - test processEvent(Match, TickerEvent)
   * - test Vector<TickerEvent> runTicker(Match, GameTicker, int)
   */
  
  /**
   * Create a test suite for testing from the shell
   *
   * @return a junit.framework.Test instance containing
   * all tests of this class
   */
  public static junit.framework.Test suite() {
    return new JUnit4TestAdapter(TeamTest.class);
  }

  /**
   * Main method, used to create a junit.textui.TestRunner
   *
   * @param args ignored for this test
   */
   public static void main(String[] args) {
     TestRunner.runAndWait(suite());
   }

}
