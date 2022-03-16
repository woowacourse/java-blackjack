package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class PlayerRunning extends Running {

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
}
