package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;
import java.util.Arrays;

public class StateFactory {

    public static State generateState(Card first, Card second) {
        Cards cards = Cards.of(Arrays.asList(first, second));

        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

}
