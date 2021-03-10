package blackjack.domain.state;

public abstract class Running implements State {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new IllegalStateException();
    }
}
