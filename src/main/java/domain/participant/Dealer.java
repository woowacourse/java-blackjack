package domain.participant;

import domain.card.Hand;

public final class Dealer extends Participant {

  private static final String DEFAULT_NAME = DealerRoster.DEFAULT.getName();

  public Dealer() {
    super();
  }

  public Dealer(final Hand hand) {
    super(hand);
  }

  @Override
  public boolean isHit() {
    return calculateScore().isDealerNeedHit();
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
