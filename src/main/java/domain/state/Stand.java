package domain;

public class Stand implements State {
    private final Hand hand;

    public Stand(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State hit(final Card card) {
        throw new UnsupportedOperationException("Stand 상태의 플레이어는 hit 할 수 없습니다.");
    }

    @Override
    public State stand() {
        return this;
    }
}
