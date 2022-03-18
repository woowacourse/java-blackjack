package blackjack.state;

public class Stand implements State {
    @Override
    public State stand() {
        throw new IllegalStateException("이미 스탠드 상태입니다.");
    }
}
