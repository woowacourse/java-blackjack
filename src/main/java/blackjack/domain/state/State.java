package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    Cards cards();

    boolean isFinished();

    State addCard(Card card);

    State stay();

    double earningsRate();
}
