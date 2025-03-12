package domain.card;

import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

  private final List<TrumpCard> cards;

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public Hand(final List<TrumpCard> cards) {
    this.cards = new ArrayList<>(cards);
  }

  public Hand(final Hand hand) {
    this.cards = new ArrayList<>(hand.getCards());
  }

  public void add(final TrumpCard card) {
    cards.add(card);
  }

  public int calculateScore() {
    int score = 0;
    for (final TrumpCard card : cards) {
      score = card.add(score);
    }

    return Rank.ifBustAceIsMIN(score, calculateAceCount());
  }

  private int calculateAceCount() {
    return (int) cards.stream()
        .filter(card -> card.isMatchRank(Rank.ACE))
        .count();
  }

  public List<TrumpCard> getCards() {
    if (cards.isEmpty()) {
      throw new BlackjackArgumentException("핸드가 비었습니다.");
    }
    return Collections.unmodifiableList(cards);
  }

  public int getCount() {
    return cards.size();
  }
}
