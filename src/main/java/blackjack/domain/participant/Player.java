package blackjack.domain.participant;

public class Player extends Participant {

    private final HitDecision hitDecision = new HitDecision();

    public Player(final String name) {
        super(new Name(name));
    }

    public void stay() {
        hitDecision.stay();
    }

    @Override
    public boolean canReceiveCard() {
        return hitDecision.wantsHit() && !isBust();
    }
}
