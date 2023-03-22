package domain.blackjack.gamestate;

public enum HandState {
    BLACKJACK,
    STAY,
    BUST,
    ;

    public boolean isEqualState(HandState handState) {
        return this == handState;
    }
}
