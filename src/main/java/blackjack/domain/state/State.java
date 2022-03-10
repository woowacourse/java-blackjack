package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;

public interface State {

    State draw(final Card card);

    State stay();

    boolean isFinished();

    Cards cards();

    GameOutcome compare(final State another);

    static State create(final Cards cards) {
        if (cards.isBlackJack()) {
            return new BlackJack(cards);
        }
        return new Running(cards);
    }
}