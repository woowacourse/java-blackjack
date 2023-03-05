package blackjack.domain;

import blackjack.domain.card.ParticipantCards;

public class Player extends Participant {
    protected Player(final ParticipantCards cards, final String name) {
        super(cards, name);
    }

    @Override
    protected boolean isHittable() {
        return !cards.isBlackJack() && !cards.isBust();
    }
}
