package blackjack.domain;

public class BustState implements State {
    private final Hand hand;

    public BustState(Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(Deck deck) {
        throw new UnsupportedOperationException("현재 상태에서 지원하지 않는 동작입니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("현재 상태에서 지원하지 않는 동작입니다.");
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
