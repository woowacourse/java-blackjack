package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final int CONDITION_HIT = 16;

    public Dealer() {
        super(NAME);
    }

    public boolean isHit() {
        return score() <= CONDITION_HIT;
    }
}
