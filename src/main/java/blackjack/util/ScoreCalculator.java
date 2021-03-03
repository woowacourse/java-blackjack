package blackjack.util;

import blackjack.domain.card.Deck;

public interface ScoreCalculator {
  int apply(Deck deck);
}
