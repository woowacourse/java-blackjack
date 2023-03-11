package domain.user;

public enum MemberStatus {
    BUST,
    NOT_BUST,
    BLACKJACK;
    
    public static final int BLACKJACK_BOUNDARY = 21;
    
    public static MemberStatus of(int score) {
        if (score > BLACKJACK_BOUNDARY) {
            return BUST;
        }
        if (score == BLACKJACK_BOUNDARY) {
            return BLACKJACK;
        }
        return NOT_BUST;
    }
    
    public boolean isBust() {
        return this == BUST;
    }
}
