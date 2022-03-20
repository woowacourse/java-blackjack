package blackjack.domain.state;

import blackjack.domain.cards.Cards;
import blackjack.domain.cards.card.Card;
import blackjack.domain.state.finished.Finished;

public interface State {
    State draw(Card card);

    State stay();

    Cards cards();

    boolean isFinished();

    int profit(final int money, final Finished state);
}
