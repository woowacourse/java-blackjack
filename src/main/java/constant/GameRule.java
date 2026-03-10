package constant;

import java.util.List;

public class GameRule {
    public static final List<String> YES_ANSWER = List.of("Y", "y");
    public static final List<String> NO_ANSWER = List.of("N", "n");

    public static final int BLACKJACK_CRITERION = 21;
    public static final int DEALER_HIT_CRITERION = 17;

    public static final int ACE_BONUS_SCORE = 10;

    public static final int MIN_PLAYER_NUMBER = 1;
    public static final int MAX_PLAYER_NUMBER = 7;

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;

    public static final int INIT_CARD_COUNT = 2;
}
