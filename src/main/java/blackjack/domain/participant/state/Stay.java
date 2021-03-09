package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public class Stay extends Finished {

    public Stay(final List<Card> cards) {
        super(cards);
    }
}
