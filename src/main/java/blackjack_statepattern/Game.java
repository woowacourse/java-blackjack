package blackjack_statepattern;

import java.util.List;

public final class Game {
    public static State start(final Card first, final Card second) {

        Cards cards = new Cards(List.of(first, second));
        if (cards.isBlackjack()) {
            return new Blackjack();
        }
        return new Hit();
    }

}
