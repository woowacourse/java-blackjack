package domain.participant;

public class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean canReceiveMoreCard() {
        return !isBust() && !isBlackjack();
    }
}
