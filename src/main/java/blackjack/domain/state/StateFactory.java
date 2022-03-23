package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class StateFactory {

    public static State createFirstState(Cards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
