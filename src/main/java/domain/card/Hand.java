package domain.card;

import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hand {

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

  public Score calculateScore() {
    final Score score = cards.stream()
        .map(TrumpCard::getScore)
        .reduce(Score::add)
        .orElse(new Score(0));
    if (hasAce()) {
      return score.withAce();
    }
    return score;
  }

  private boolean hasAce() {
    return cards.stream().anyMatch(TrumpCard::isAce);
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
