package domain;

import java.util.function.IntUnaryOperator;

public enum GameOutcome {
    BLACKJACK(revenue -> (int) (revenue * 1.5)),
    WIN(revenue -> revenue),
    DRAW(revenue -> 0),
    LOSE(revenue -> -revenue),
    ;

    private static final int BLACK_JACK_SCORE = 21;
    private static final int INITIAL_DRAW_SIZE = 2;

    private final IntUnaryOperator money;

    GameOutcome(final IntUnaryOperator money) {
        this.money = money;
    }

    public int calculateRevenue(int bettingMoney) {
        return money.applyAsInt(bettingMoney);
    }

    public static GameOutcome of(int criteria, int comparison, int cardSize) {
        if (isBlackjack(criteria, comparison, cardSize)) {
            return BLACKJACK;
        }
        if (isBust(criteria)) {
            return LOSE;
        }
        if (criteria > comparison || isBust(comparison)) {
            return WIN;
        }
        if (criteria < comparison) {
            return LOSE;
        }
        return DRAW;
    }

    private static boolean isBust(final int score) {
        return score > BLACK_JACK_SCORE;
    }

    private static boolean isBlackjack(final int criteria, final int comparison, final int cardSize) {
        return cardSize == INITIAL_DRAW_SIZE && criteria == BLACK_JACK_SCORE && comparison != BLACK_JACK_SCORE;
    }
}
