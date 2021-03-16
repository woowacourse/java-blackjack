package blackjack.domain.state;

import blackjack.domain.card.UserDeck;
import blackjack.domain.user.Money;

public interface State {

    boolean isFinished();

    State draw(UserDeck userDeck);

    double profit(Money money);
}
