package domain.constant;

public enum BlackJackRule {

    INITIAL_CARD_COUNT(2),

    BUST_NUMBER (21),
    ACE_WEIGHT(10),

//    PLAYER_PLAYING_THRESHOLD(20),
//    DEALER_PLAYING_THRESHOLD (16),
//    DEFAULT_HAND_SCORE(0),
    ;
    private final int value;

    BlackJackRule(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
