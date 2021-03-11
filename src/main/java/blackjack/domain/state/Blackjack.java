package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finished {
    public Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double money) {
        return money * 1.5;
    }
}
