package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Draw extends Result {

    Draw(Cards cards) {
        super(cards);
    }

    @Override
    public double prizeRate() {
        return 0;
    }
}
