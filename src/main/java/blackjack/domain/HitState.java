package blackjack.domain;

public class HitState implements State {
    private final Hand hand;

    public HitState(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Deck deck) {
        Card card = deck.draw();
        Hand newHand = hand.add(card);
        if (newHand.isBust()) {
            return new BustState(newHand);
        }
        return new HitState(newHand);
    }

    @Override
    public State stand() {
        return new StandState(hand);
    }

    @Override
    public Score calculateHand() {
        return null;
    }

    public boolean isFinished() {
        return false;
    }

    public Hand getHand() {
        return hand;
    }
}
