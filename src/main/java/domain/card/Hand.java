package domain.card;

import java.util.ArrayList;
import java.util.List;

public final class Hand {

  private final List<TrumpCard> cards;

  public Hand() {
    this.cards = new ArrayList<>();
  }

  public Hand(final List<TrumpCard> cards) {
    this.cards = new ArrayList<>(cards);
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

  public List<TrumpCard> getCards() {
    return new ArrayList<>(cards);
  }

  public int getCount() {
    return cards.size();
  }

  public boolean isBlackjack() {
    return calculateScore().isBlackjack(getCount());
  }

  private boolean hasAce() {
    return cards.stream().anyMatch(TrumpCard::isAce);
  }

}
