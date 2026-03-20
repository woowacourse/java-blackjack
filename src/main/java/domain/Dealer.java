package domain;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    @Override
    public boolean canHit() {
        return getScore() <= HIT_THRESHOLD;
    }

    public Card getInitialCard() {
        return getCards().getFirst();
    }
}
