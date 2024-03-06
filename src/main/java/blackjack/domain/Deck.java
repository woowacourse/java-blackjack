package blackjack.domain;

import blackjack.domain.card.Card;

@FunctionalInterface
public interface Deck {
    Card drawCard();
}
