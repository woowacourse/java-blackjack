package domain.gameresult;

import domain.player.Bet;

public class BetCounter {

    public static final float BLACKJACK_MULTIPLY_VALUE = 1.5f;

    private BetCounter() {
    }

    public static Bet draw() {
        return Bet.zero();
    }

    public static Bet blackJack(Bet bet) {
        return bet.multiply(BLACKJACK_MULTIPLY_VALUE);
    }

    public static Bet win(Bet bet) {
        return bet;
    }

    public static Bet lose(Bet bet) {
        return bet.toNegative();
    }
}
