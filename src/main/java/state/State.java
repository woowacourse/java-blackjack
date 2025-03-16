package state;

import card.Card;
import card.Cards;

public interface State {

    State draw(final Card card);
    State stand();
    boolean isFinished();
    Cards cards();
    double profit(final int bettingMoney);
}
