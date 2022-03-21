package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Lose extends Result {

    Lose(Cards cards) {
        super(cards);
    }

    @Override
    public double prizeRate() {
        return -1;
    }
}
