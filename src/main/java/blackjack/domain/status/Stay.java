package blackjack.domain.status;

import blackjack.domain.participant.Cards;

public class Stay extends Finished{
    public Stay(Cards cards) {
        super(cards);
    }
}
