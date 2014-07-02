package wmticker.test;

import java.util.Vector;

import wmticker.EventType;
import wmticker.GameTicker;
import wmticker.Match;
import wmticker.Player;
import wmticker.Position;
import wmticker.Team;
import wmticker.TickerEvent;

/**
 * This interface provides a large collection of methods needed for running and
 * testing a ticker.
 * 
 * @author Guido R&ouml;&szlig;ling (roessling@acm.org)
 */
public interface TestHarness {

  // Player methods
  /**
   * creates a new player
   * 
   * @param name the player's name
   * @param nr the player's number
   * @param pos the player's position
   * @return a new player with the initial data passed in
   */
  public Player createPlayer(String name, int nr, Position pos);

  /**
   * explicitly changes a player's position
   * 
   * @param p the player changing positions
   * @param targetPosition the new target position of the player
   */
  public void changePosition(Player p, Position targetPosition);

  
  // Team methods

  /**
   * creates a new team with the given name and players
   * 
   * @param name the name of the team
   * @param players the Vector of players
   * @return the created team
   */
  public Team createTeam(String name, Vector<Player> players);

  /**
   * returns the players of a team as a Vector
   * 
   * @param t the team
   * @return the team's players as a Vector
   */
  public Vector<Player> members(Team t);

  
  // TickerEvent methods
  
  /**
   * creates a new TickerEvent object describing an "event" in the game
   * 
   * @param whichMatch the current match
   * @param when the minute of gameplay, between 0 and 90
   * @param whichTeam the team for the event (null for kick-off, halftime, and
   * finished, else the team "committing" the event (scoring a goal, receiving
   * a card, ...)
   * @param who the concrete player, if approriate (null for kick-off, halftime,
   * and finished)
   * @param what the type of the event
   * @return the concrete TickerEvent created based on the data passed in
   */
  public TickerEvent createEvent(Match whichMatch, int when, Team whichTeam,
      Player who, EventType what);
  

  // Match methods

  /**
   * creates a concrete soccer match between two teams
   * 
   * @param firstTeam the first ("home") team
   * @param secondTeam the second ("visitor") team
   * @return the concrete soccer match instance
   */
  public Match createMatch(Team firstTeam, Team secondTeam);

  // GameTicker methods
  
  /**
   * creates a new GameTicker
   */
  public GameTicker createGameTicker();
  
  /**
   * generates a new random event for the match and ticker given
   * 
   * @param m the concrete match
   * @param t the current GameTicker
   * @param currentTime the current playing time (between 0 and 90)
   * @return the concrete generated TickerEvent
   */
  public TickerEvent generateRandomEvent(Match m, GameTicker t, int currentTime);

  /**
   * updates the match based on the given (validated) event
   * 
   * @param m the match
   * @param event the event to process
   */
  public void processEvent(Match m, TickerEvent event);

  /**
   * runs the ticker by ensuring the central events (kick-off, halftime, finished)
   * are set and only validated events are included.
   * 
   * @param match the current match
   * @param t the GameTicker
   * @param intensity indicates how often events occur: if the current event took
   * place in minute x, the next event will be sometime in [x+0, x+intensity).
   * @return a Vector of TickerEvents
   */
  public Vector<TickerEvent> runTicker(Match match, GameTicker t, int intensity);


  /**
   * validates if the event passed in is legal. An event is only legal
   * if it belongs to the match passed in, and is actually "possible". For example,
   * a player sent off by a red card cannot score a goal later on; a player cannot
   * receive a yellow/red card without having received a yellow card before.
   * 
   * @param match the current match
   * @param t the GameTicker
   * @param event the event to validate
   * @return true if the event is valid, else false
   */
  public boolean validateEvent(Match match, GameTicker t, TickerEvent event);

}
