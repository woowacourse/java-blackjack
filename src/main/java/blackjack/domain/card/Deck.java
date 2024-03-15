package blackjack.domain.card;

import java.util.List;

public interface Deck {
    Card drawCard();

    List<Card> drawInitialCards();
}
