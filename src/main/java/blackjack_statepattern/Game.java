package blackjack_statepattern;

public class Game {
    public static Hit start(final Card first, final Card second) {
        return new Hit();
    }

}
