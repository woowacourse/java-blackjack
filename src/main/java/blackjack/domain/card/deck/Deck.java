package blackjack.domain.card.deck;

import blackjack.domain.card.Card;
import java.util.List;

public interface Deck {

    Card pick();

    List<Card> pickTwoCards();
}
