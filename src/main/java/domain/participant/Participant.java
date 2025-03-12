package domain.participant;

import domain.card.Deck;
import domain.card.Hand;
import domain.card.Score;
import domain.card.TrumpCard;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

  private static final int INITIAL_DEAL_CARD_COUNT = 2;

  private final Hand hand;

  public Participant() {
    hand = new Hand();
  }

  public Participant(final Hand hand) {
    this.hand = new Hand(hand);
  }

  public abstract boolean isHit();

  public abstract boolean isDealer();

  public abstract String getName();

  public void initialDeal(final Deck deck) {
    for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
      final TrumpCard card = deck.draw();
      hit(card);
    }
  }

  public void hit(final TrumpCard card) {
    isHit();
    hand.add(card);
  }

  public Score calculateScore() {
    return hand.calculateScore();
  }

  public boolean isBlackjack() {
    return hand.getCount() == 2 && calculateScore().isBlackjack();
  }

  public List<TrumpCard> getCards() {
    return hand.getCards();
  }

  public int getHandCount() {
    return hand.getCount();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Participant that = (Participant) o;
    return Objects.equals(getName(), that.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getName());
  }
}

