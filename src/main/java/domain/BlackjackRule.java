package domain;

public class BlackjackRule {
    public static final int BLACKJACK_TARGET_SCORE = 21;
    public static final int MAX_PARTICIPANTS_COUNT = 16;
    public static final int DEALER_MAX_HIT_SCORE = 16;
    public static final int BLACKJACK_CARD_COUNT = 2;
    private static final int ACE_MAX_SCORE = 11;
    private static final int ACE_MIN_SCORE = 1;
    public static final int ACE_SCORE_DIFFERENCE = ACE_MAX_SCORE - ACE_MIN_SCORE;

    private BlackjackRule() {}
}
