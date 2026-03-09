package blackjack.domain.participant;

public class Player extends Participant {

    private boolean isStayed = false;

    public Player(final String name) {
        super(new Name(name));
    }

    public void stay() {
        isStayed = true;
    }

    @Override
    public boolean canReceiveCard() {
        return !isStayed && !isBust();
    }
}
