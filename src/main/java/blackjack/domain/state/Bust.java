package blackjack.domain.state;

import blackjack.domain.user.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }
}
