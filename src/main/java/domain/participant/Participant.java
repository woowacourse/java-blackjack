package domain.participant;

import domain.Bet;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.Score;
import domain.card.TrumpCard;
import java.util.List;
import java.util.Objects;

/**
 * 블랙잭 게임의 참가자를 나타내는 클래스입니다. 제너릭 타입 T는 참가자가 수행하는 역할(Role)을 지정합니다.
 * <p>
 * 내부적으로는 구체적인 역할 타입을 유지하지만, 외부로는 다형적 인터페이스를 제공합니다.
 *
 * @param <T> 참가자의 역할 타입 (Dealer 또는 Player)
 */
public final class Participant<T extends Role> {

  private static final int INITIAL_DEAL_CARD_COUNT = 2;

  private final T role;
  private final Hand hand;

  public Participant(final T role) {
    this.role = role;
    this.hand = new Hand();
  }

  public Participant(final T role, final List<TrumpCard> cards) {
    this.role = role;
    this.hand = new Hand(cards);
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
