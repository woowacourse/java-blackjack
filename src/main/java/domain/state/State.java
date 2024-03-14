package domain.state;

import domain.card.Card;
import domain.card.Deck;

public interface State {
    State draw(Card card);

    State stand();

    boolean isFinished();

    Deck getCards();

    double profit();
}
