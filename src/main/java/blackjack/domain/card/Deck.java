package blackjack.domain.card;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface Deck {
    Card drawCard();
}
