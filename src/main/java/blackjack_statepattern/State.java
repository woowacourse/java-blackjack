package blackjack_statepattern;

public interface State {
    State draw(Card card);
}
