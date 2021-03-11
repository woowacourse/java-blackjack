package blackjack.domain.state;

import blackjack.domain.user.Cards;

public class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
    }
}
