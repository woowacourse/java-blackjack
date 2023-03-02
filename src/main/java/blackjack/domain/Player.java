package blackjack.domain;

public class Player extends Participant {
    protected Player(final ParticipantCards cards) {
        super(cards);
    }

    @Override
    protected boolean isHittable() {
        return !cards.isBust();
    }
}
