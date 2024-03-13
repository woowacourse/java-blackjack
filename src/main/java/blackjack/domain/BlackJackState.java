package blackjack.domain;

public class BlackJackState implements State {
    private final Hand hand;

    public BlackJackState(Hand hand) {
        this.hand = hand;
    }
}
