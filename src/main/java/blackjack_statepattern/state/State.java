package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public interface State {
    State draw(Card card);

    State stay();

    Cards cards();

    boolean isFinished();

    double profit(double money);
}
