package blackjack.domain;

public class HitState implements State {
    private final Hand hand;

    public HitState(Hand hand) {
        this.hand = hand;
    }
}
