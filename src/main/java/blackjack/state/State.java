package blackjack.state;

import blackjack.domain.card.Card;

public interface State {
    State draw(Card card);

    Cards cards();

    State stay();

    double profit(double money);

    boolean isFinished();
}
