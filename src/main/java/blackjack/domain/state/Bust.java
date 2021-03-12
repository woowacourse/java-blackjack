package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finish {
    public Bust(final Cards cards) {
        super(cards);
    }
}
