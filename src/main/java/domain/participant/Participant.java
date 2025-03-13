package domain.participant;

import domain.Money;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.Score;
import domain.card.TrumpCard;
import java.util.List;
import java.util.Objects;

public final class Participant<T extends Role> {

  private static final int INITIAL_DEAL_CARD_COUNT = 2;

  private final T role;
  private final Hand hand;

  public Participant(T role) {
    this.role = role;
    this.hand = new Hand();
  }

  public Participant(T role, List<TrumpCard> cards) {
    this.role = role;
    this.hand = new Hand(cards);
  }

  public Participant(T role, Hand hand) {
    this.role = role;
    this.hand = hand;
  }

  public boolean isHit() {
    return role.isHit(calculateScore());
  }

  public String getName() {
    return role.getName();
  }

  public Money getMoney() {
    return role.getMoney();
  }

  public Participant<T> initialDeal(final Deck deck) {
    for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
      final TrumpCard card = deck.draw();
      hit(card);
    }
    return new Participant<>(this.role, getCards());
  }

  public Participant<? extends Role> hit(final TrumpCard card) {
    isHit();
    hand.add(card);
    return new Participant<>(role, getCards());
  }

  public boolean isBlackjack() {
    return hand.isBlackjack();
  }

  public Score calculateScore() {
    return hand.calculateScore();
  }

  public List<TrumpCard> getCards() {
    return hand.getCards();
  }

  public T getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Participant<?> that = (Participant<?>) o;
    return Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(role);
  }
}
