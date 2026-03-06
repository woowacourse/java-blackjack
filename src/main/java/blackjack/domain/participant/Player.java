package blackjack.domain.participant;

public class Player extends Participant {

    private boolean wantsHit = true;

    public Player(final String name) {
        super(new Name(name));
    }

    public void stay() {
        wantsHit = false;
    }

    @Override
    public boolean canReceiveCard() {
        return wantsHit && !isBust();
    }
}
