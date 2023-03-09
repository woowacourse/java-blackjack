package domain.participant;

public final class Player extends Participant {

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isHittableForPlayer();
    }
}
