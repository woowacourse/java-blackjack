package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Win extends Result {
    Win(Cards cards) {
        super(cards);
    }

    @Override
    public double prizeRate() {
        return 1;
    }
}
