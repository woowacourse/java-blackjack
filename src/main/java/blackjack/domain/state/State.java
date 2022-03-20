package blackjack.domain.state;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;

public interface State {
    State draw(Card card);

    State stay();

    Cards cards();

    boolean isFinished();

    double profit(double money);
}
