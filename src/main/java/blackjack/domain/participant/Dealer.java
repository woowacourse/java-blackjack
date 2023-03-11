package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {

    public static final int DRAWING_BOUNDARY = 17;

    public Card getFirstCard() {
        return List.copyOf(super.getCards()).get(0);
    }
}
