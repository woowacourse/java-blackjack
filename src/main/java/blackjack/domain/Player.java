package blackjack.domain;

public class Player extends Participant {
    protected Player(final ParticipantCards cards, final String name) {
        super(cards, name);
    }

    @Override
    protected boolean isHittable() {
        return !cards.isBust();
    }
}
