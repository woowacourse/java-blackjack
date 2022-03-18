package blackjack.state;

public class Blackjack implements State {
    @Override
    public State stand() {
        throw new IllegalStateException("블랙잭 상태에서는 스탠드할 수 없습니다.");
    }
}
