package blackjack.domain.participant.state;

public abstract class PlayState implements State {

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final boolean isBlackjack() {
        return false;
    }

    @Override
    public final boolean isBust() {
        return false;
    }

}
