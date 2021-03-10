package blackjack.domain.state;

public interface PlayerState {
    boolean isFinished();

    PlayerState keepContinue(boolean input);
}
