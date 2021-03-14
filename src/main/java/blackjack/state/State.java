package blackjack.state;

import blackjack.domain.User;
import blackjack.domain.card.Card;

public interface State {
    State draw(Card card);

    Cards cards();

    State stay();

    double profit(double money, User user);

    boolean isFinished();
}
