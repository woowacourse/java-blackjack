package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class FirstTurn {
    public static State generateState(final Cards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
