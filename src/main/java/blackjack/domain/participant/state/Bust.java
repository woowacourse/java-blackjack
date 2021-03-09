package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public class Bust extends Finished {

    public Bust(final List<Card> cards) {
        super(cards);
    }
}
