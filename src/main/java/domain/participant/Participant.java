package domain.participant;

import domain.Bet;
import domain.card.Hand;
import domain.card.Score;
import domain.card.TrumpCard;
import java.util.List;
import java.util.Objects;

public final class Participant {

  private final Role role;
  private final Hand hand;

  public Participant(final Role role) {
    this.role = role;
    this.hand = new Hand();
  }

  public Participant(final Role role, final List<TrumpCard> cards) {
    this.role = role;
    this.hand = new Hand(cards);
  }

  public Participant hit(final TrumpCard card) {
    final List<TrumpCard> cards = getCards();
    cards.add(card);
    return new Participant(role, cards);
  }

  public Score calculateScore() {
    return hand.calculateScore();
  }

  public boolean isBlackjack() {
    return hand.isBlackjack();
  }

  public boolean isHit() {
    return role.isHit(calculateScore());
  }

  public String getName() {
    return role.getName();
  }

  public Bet getBet() {
    return role.getBet();
  }

  public List<TrumpCard> getCards() {
    return hand.getCards();
  }

  public Role getRole() {
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
    Participant that = (Participant) o;
    return Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(role);
  }
}
