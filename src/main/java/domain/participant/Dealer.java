package domain.participant;

public class Dealer extends Participant {

    private static final int STAND_SCORE = 17;

    public Dealer() {
        super(new Name("딜러"), new Hand());
    }

    @Override
    public boolean canHit() {
        return getScore() < STAND_SCORE;
    }


}
