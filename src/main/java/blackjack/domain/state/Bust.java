package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {
    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double money) {
        return 0;
    }
}
