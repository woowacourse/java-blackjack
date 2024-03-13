package blackjack.domain;

public class InitialState implements State {

    public InitialState() {
    }

    public State draw(Deck deck) {
        Hand hand = Hand.of(deck.draw(), deck.draw());
        if (hand.isBlackJack()) {
            return new BlackJackState(hand);
        }
        return new HitState(hand);
    }
}
