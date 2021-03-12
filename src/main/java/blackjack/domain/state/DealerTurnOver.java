package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class DealerTurnOver extends Finish {
    public DealerTurnOver(Cards cards) {
        super(cards);
    }
}
