package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class BlackjackScoreCalculator implements ScoreCalculator {
  private static final int ACE_EXCEPTION_NUMBER = 10;
  private static final int WINNING_NUMBER = 21;

  @Override
  public int apply(Deck deck) {
    return getScoreIncludeAce(deck, getScoreExceptAce(deck));
  }

  private int getScoreExceptAce(Deck deck) {
    return deck.getCards().stream().filter(card -> !card.isAce()).mapToInt(Card::getScore).sum();
  }

  private int getScoreIncludeAce(Deck deck, int scoreExceptAce) {
    return deck.getCards().stream().filter(Card::isAce)
        .mapToInt(card -> scoreCalculate(scoreExceptAce, card)).sum() + scoreExceptAce;
  }

  private int scoreCalculate(int scoreExceptAce, Card card) {
    if (scoreExceptAce + card.getScore() + ACE_EXCEPTION_NUMBER > WINNING_NUMBER) {
      return card.getScore();
    }
    return card.getScore() + ACE_EXCEPTION_NUMBER;
  }
}
