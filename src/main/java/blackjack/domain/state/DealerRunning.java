package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class DealerRunning extends Running {

    private DealerRunning(final Cards cards) {
        super(cards);
    }

    public static BlackjackGameState createDealerGameState(final Cards cards) {
        return crerateDealerGameState(cards);
    }

    private static BlackjackGameState crerateDealerGameState(final Cards cards) {
        if (cards.isMaxScoreBust()) {
            return new Bust(cards, cards.maxScore());
        }
        if (cards.maxScore() >= 17) {
            return new Stand(cards, cards.maxScore());
        }
        return new DealerRunning(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        cards.addCard(card);
        return crerateDealerGameState(cards);
    }
}
