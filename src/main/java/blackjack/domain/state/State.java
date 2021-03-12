package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {
    State draw(Card card);

    boolean isFinish();

    State stay();

    int calculateScore();

    Cards cards();
}
