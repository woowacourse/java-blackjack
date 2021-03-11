package blackjack.domain.status;

import blackjack.domain.card.Card;

public class StartRound {
    public static State draw(final Card first, final Card second) {
        Cards cards = new Cards(first, second);
        if (cards.isBlackJack())
            return new Blackjack(cards);
        if (cards.isBust())
            return new Bust(cards);
        return new Hit(cards);
    }
}
