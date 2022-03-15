package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finished {

    public Stand(final Cards cards) {
        super(cards);
    }

    @Override
    public int score() {
        return 0;
    }
}
