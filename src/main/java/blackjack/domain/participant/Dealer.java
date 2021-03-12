package blackjack.domain.participant;

public class Dealer extends Participant {
    public static final String DEALER = "딜러";
    public static final int STAY_THRESHOLD = 17;

    public Dealer() {
        super(new Name(DEALER), 1);
    }

    public boolean shouldDraw() {
        return cardResult() < STAY_THRESHOLD;
    }
}
