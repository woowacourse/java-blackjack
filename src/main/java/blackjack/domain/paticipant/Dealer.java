package blackjack.domain.paticipant;

import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.DealerRunning;

public class Dealer extends AbstractParticipant {

    private BlackjackGameState gameState;

    private Dealer(final String name, final BlackjackGameState gameState) {
        super(name, gameState);
    }

    public Dealer(final String name, final Cards cards) {
        super(name, DealerRunning.createDealerGameState(cards));
    }
}
