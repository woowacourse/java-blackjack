package domain.gamer;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hand cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean canHit() {
        return HIT_THRESHOLD > cards.calculateScore();
    }
}
