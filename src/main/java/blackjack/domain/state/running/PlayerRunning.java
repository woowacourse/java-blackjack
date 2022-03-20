package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stand;

public final class PlayerRunning extends Running {

    public PlayerRunning(final Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards, cards.score());
        }
        return new PlayerRunning(cards);
    }

    @Override
    public BlackjackGameState stay() {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Stand(cards, cards.score());
    }
}
