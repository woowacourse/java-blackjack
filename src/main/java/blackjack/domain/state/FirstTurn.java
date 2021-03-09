package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Arrays;

public class FirstTurn {

    public static State draw(final Card first, final Card second) {
        final Cards cards = Cards.of(Arrays.asList(first, second));
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
