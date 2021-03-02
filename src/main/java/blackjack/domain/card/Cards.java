package blackjack.domain.card;

import blackjack.exception.CardDuplicateException;
import java.util.Collections;
import java.util.List;

public class Cards {

  private final List<Card> cards;

  public Cards(final List<Card> cards) {
    duplicateValidate(cards);

    Collections.shuffle(cards);
    this.cards = cards;
  }

  private void duplicateValidate(List<Card> cards) {
    if (cards.size() != cards.stream().distinct().count()) {
      throw new CardDuplicateException();
    }
  }

}
