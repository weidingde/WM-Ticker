package wmticker;

/**
 * enumeration of possible events in a soccer match
 * 
 * @author Guido R&ouml;&szlig;ling (roessling@acm.org)
 */
public enum EventType {
  KICK_OFF, // Anstoss
  OFFSIDES, // Abseits
  MISS, // vorbei geschossen
  PENALTY_GOAL, // Elfmeter mit Tor
  PENALTY_MISS, // Elfmeter, vergeben
  GOAL, // Tor
  OWN_GOAL, // Eigentor
  FOUL, // Foul
  YELLOW_CARD, // Gelbe Karte
  YELLOW_RED_CARD, // Gelb-rote Karte
  RED_CARD, // Rote Karte
  HALFTIME, // Halbzeit
  FINISHED // Abpfiff
}
