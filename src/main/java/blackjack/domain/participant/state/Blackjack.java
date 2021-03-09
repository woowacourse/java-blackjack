package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public class Blackjack extends Finished {

    public Blackjack(final List<Card> cards) {
        super(cards);
    }
}
