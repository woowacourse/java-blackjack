package domain.user;

public enum MemberStatus {
    BUST,
    NOT_BUST,
    BLACKJACK;
    
    public static final int BLACKJACK_BOUNDARY = 21;
    public static final int INITIAL_HAND_SIZE = 2;
    
    public static MemberStatus of(int score, int handSize) {
        if (score > BLACKJACK_BOUNDARY) {
            return BUST;
        }
        if (score == BLACKJACK_BOUNDARY && handSize == INITIAL_HAND_SIZE) {
            return BLACKJACK;
        }
        return NOT_BUST;
    }
    
    public boolean isBust() {
        return this == BUST;
    }
    
    public boolean isBlackJack() {
        return this == BLACKJACK;
    }
}
