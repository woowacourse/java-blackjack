package blackjack.domain.participant;

import blackjack.domain.game.ParticipantCards;

public class Player extends Participant {
    public Player(final ParticipantCards cards, final String name) {
        super(cards, name);
    }

    @Override
    public boolean isHittable() {
        return !cards.isBlackJack() && !cards.isBust();
    }
}
