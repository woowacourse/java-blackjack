package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class BlackjackWin extends Result {
    BlackjackWin(Cards cards) {
        super(cards);
    }

    @Override
    public double prizeRate() {
        return 1.5;
    }
}
