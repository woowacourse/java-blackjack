package blackjack.domain.state;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {

    State draw(Card card);

    State stay();

    boolean isFinished();

    boolean isBust();

    boolean isBlackjack();

    Money profit(Money money);

    int calculateScore();

    Cards cards();
}
