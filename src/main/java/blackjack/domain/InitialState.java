package blackjack.domain;

public class InitialState implements State {

    public InitialState() {
    }

    @Override
    public State draw(Deck deck) {
        Hand hand = Hand.of(deck.draw(), deck.draw());
        if (hand.isBlackJack()) {
            return new BlackJackState(hand);
        }
        return new HitState(hand);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("현재 상태에서 지원하지 않는 동작입니다.");
    }

    @Override
    public Score calculateHand() {
        throw new UnsupportedOperationException("현재 상태에서 지원하지 않는 동작입니다.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Hand getHand() {
        throw new UnsupportedOperationException("현재 상태에서 지원하지 않는 동작입니다.");
    }
}
