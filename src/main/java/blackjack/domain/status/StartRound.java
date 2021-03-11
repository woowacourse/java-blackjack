package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

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
