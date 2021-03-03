package blackjack.participant;

public class Dealer extends Participant{
    public static final String DEALER = "딜러";
    public static final int STAY_THRESHOLD = 17;

    public boolean isStay() {
        return calculateResult() >= STAY_THRESHOLD;
    }

    @Override
    String getName() {
        return DEALER;
    }
}
