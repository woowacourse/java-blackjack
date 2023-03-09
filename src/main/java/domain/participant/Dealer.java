package domain.participant;

public final class Dealer extends Participant {

    private static final Name DEFAULT_DEALER = new Name("딜러");

    public Dealer() {
        super(DEFAULT_DEALER);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isHittableForDealer();
    }
}
