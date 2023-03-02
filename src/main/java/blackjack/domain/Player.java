package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    protected Player(final ParticipantCards cards) {
        super(cards);
    }

    @Override
    protected void hit(final Card card) {
        cards.receive(card);
    }

    @Override
    protected List<Card> open(final int cardCount) {
        return cards.open(cardCount);
    }
}
