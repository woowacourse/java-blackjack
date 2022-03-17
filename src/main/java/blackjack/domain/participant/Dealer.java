package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean isHit() {
        return score().isHit();
    }
}
