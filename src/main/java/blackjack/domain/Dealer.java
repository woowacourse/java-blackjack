package blackjack.domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    private static final int HIT_THRESHOLD = 16;

    private final String name = DEALER_NAME;

    public boolean isHittable() {
        return getScore() <= HIT_THRESHOLD;
    }

    public String getName() {
        return name;
    }

    public Card getFirstCard() {
        return participantCards.getFirstCard();
    }

}
