package domain.participant;

import constant.DealerRoster;
import domain.card.Hand;

public final class Dealer extends Participant {

  private static final int STAND_SCORE = 17;
  private static final String DEFAULT_NAME = DealerRoster.DEFAULT.getName();

  public Dealer() {
    super();
  }

  public Dealer(final Hand hand) {
    super(hand);
  }

  @Override
  public boolean isHit() {
    return calculateScore() < STAND_SCORE;
  }

  @Override
  public boolean isDealer() {
    return true;
  }

  @Override
  public String getName() {
    return DEFAULT_NAME;
  }
}
