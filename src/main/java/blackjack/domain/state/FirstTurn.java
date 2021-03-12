package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.Arrays;

public class FirstTurn {
    public static State draw(final Card firstCard, final Card secondCard) {
        final Cards cards = new Cards(Arrays.asList(firstCard, secondCard));
        if (cards.calculateScore().isBlackjack()) {
            return new Blackjack();
        }
        return new Hit(firstCard, secondCard);
    }
}
