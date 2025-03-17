package domain.profit;

import domain.gamer.Gamer;

public enum Outcome {
    WIN_BLACKJACK,
    WIN,
    LOSE,
    DRAW;

    public static Outcome compare(final Gamer gamer, final Gamer otherGamer) {
        final int result = gamer.compareTo(otherGamer);

        if (result == 1) {
            if (gamer.isBlackjack()) {
                return WIN_BLACKJACK;
            }
            return WIN;
        }
        if (result == 0) {
            return DRAW;
        }
        return LOSE;
    }
}
