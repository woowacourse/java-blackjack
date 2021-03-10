package blackjack.domain.state;

public class Stay implements PlayerState {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public PlayerState keepContinue(boolean input) {
        throw new IllegalArgumentException("옳지 않은 곳에서 호출");
    }
}
