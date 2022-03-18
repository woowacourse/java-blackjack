package blackjack.state;

public final class HitTurn implements State {
    @Override
    public State stand() {
        return new Stand();
    }
}
