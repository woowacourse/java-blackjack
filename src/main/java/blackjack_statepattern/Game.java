package blackjack_statepattern;

import java.util.stream.Stream;

public class Game {
    public static Object start(final Card first, final Card second) {

        int sum = first.score() + second.score();
        boolean hasAce = Stream.of(first, second)
                .anyMatch(Card::isAce);
        if (11 == sum && hasAce) {
            return new Blackjack();
        }
        return new Hit();
    }

}
