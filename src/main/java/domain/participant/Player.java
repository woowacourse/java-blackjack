package domain.participant;

import domain.card.Hand;

public final class Player extends Participant {

  private final String name;

  public Player(final String name) {
    super();
    this.name = name;

  }

  public Player(final String name, final Hand hand) {
    super(hand);
    this.name = name;

  }

  @Override
  public boolean isHit() {
    return !calculateScore().isBust();
  }

  @Override
  public boolean isDealer() {
    return false;
  }

  @Override
  public String getName() {
    return name;
  }
}
