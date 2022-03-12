package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public interface Player {

    void addCard(Card card);

    boolean isOverLimit(int limit);

    String getName();

    Deck getDeck();

    boolean isDealer();
}
