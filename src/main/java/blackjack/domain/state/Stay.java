package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double profitRatio() {
        return 1;
    }
}
