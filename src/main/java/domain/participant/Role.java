package domain.participant;

import domain.Bet;
import domain.card.Score;

public interface Role {

  boolean isHit(final Score score);

  String getName();

  Bet getBet();
}
