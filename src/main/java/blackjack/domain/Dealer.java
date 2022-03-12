package blackjack.domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    private static final int HEAT_THRESHOLD = 16;
    private static final int FIRST_INDEX = 0;

    private final String name = DEALER_NAME;

    public boolean isHit() {
        return super.getScore() <= HEAT_THRESHOLD;
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return super.getCards().get(FIRST_INDEX);
    }

}
