package domain.participant;

public class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    public boolean canReceiveMoreCard() {
        return !isBust() && !isBlackjack();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
