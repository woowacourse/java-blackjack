package blackjack.domain.status;

import blackjack.domain.participant.Cards;

public class Bust extends Finished {
    public Bust(Cards cards) {
        super(cards);
    }

}
