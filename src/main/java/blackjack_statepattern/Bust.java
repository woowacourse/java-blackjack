package blackjack_statepattern;

public class Bust implements State{
    @Override
    public State draw(Card card) {
        return null;
    }
}
