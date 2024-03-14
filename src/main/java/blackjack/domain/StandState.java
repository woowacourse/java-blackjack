package blackjack.domain;

public class StandState implements State {
    public StandState(Hand hand) {
    }

    @Override
    public State draw(Deck deck) {
        return null;
    }

    @Override
    public State stand() {
        return null;
    }

    @Override
    public Score calculateHand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Hand getHand() {
        return null;
    }
}
