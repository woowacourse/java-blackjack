package blackjack.domain.participant;

public class Dealer extends User {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean shouldDrawCard() {
        return calculateCardsValue() < HIT_THRESHOLD;
    }
}
