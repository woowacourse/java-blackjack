package domain.participant;

public class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    public boolean canReceiveMoreCard() {
        return !isBust() && !isBlackjack();
    }
}
