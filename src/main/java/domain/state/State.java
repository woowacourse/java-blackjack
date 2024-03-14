package domain.state;

import domain.card.Cards;

public interface State {
    State draw();

    State stand();

    boolean isFinished();

    Cards getCards();

    double profit();
}
