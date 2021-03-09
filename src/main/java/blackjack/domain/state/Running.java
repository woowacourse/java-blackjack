package blackjack.domain.state;

public abstract class Running extends Started {

    @Override
    public boolean isFinished() {
        return false;
    }
}
