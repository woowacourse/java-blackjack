package blackjack.utils;

public abstract class Constants {
    public static final double BLACKJACK_EARNING_RATE = 1.5;
    public static final int WIN_EARNING_RATE = 1;
    public static final int DRAW_EARNING_RATE = 0;
    public static final int LOSE_EARNING_RATE = -1;

    public static final int INITIAL_CARD_COUNT = 2;

    public static final String DEFAULT_NAME_OF_DEALER = "딜러";

    public static final String EXPRESSION_OF_HIT = "y";
    public static final String EXPRESSION_OF_STAY = "n";

    public static final int DEALER_MIN_SCORE_POLICY = 16;
}
