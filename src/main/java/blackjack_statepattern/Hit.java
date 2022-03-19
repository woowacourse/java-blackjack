package blackjack_statepattern;

public class Hit implements State {

    @Override
    public State draw(final Card card) {
        return new Hit();
    }
}
