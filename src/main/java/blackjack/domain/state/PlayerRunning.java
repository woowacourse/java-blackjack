package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class PlayerRunning extends Running {

    public PlayerRunning(final Cards cards) {
        super(cards);
    }

    @Override
    public final BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards, cards.score());
        }
        return new PlayerRunning(cards);
    }

    @Override
    public final BlackjackGameState stay() {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Stand(cards, cards.score());
    }
}
