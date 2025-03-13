package domain.participant;

import domain.Money;
import domain.card.Score;

public interface Role {

  boolean isHit(final Score score);

  String getName();

  Money getMoney();
}
