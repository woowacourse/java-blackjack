package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class StateFactory {

    public static State draw(Cards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack();
        }
        return new Hit(cards);
    }
}
