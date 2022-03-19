package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class DealerBust extends Bust {

    DealerBust(PlayerCards cards) {
        super(cards);
    }
}
