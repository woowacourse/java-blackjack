package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class StateFactory {
    public static PlayerState drawTwoCards(Cards cards) {
        if (cards.calculateIncludeAce() == 21) {
            return new BlackJack();
        }
        return new Hit();
    }
}
