package blackjack.domain;

public class Dealer extends Player {
    private static final int DEALER_CRITICAL_SCORE = 16;
    private static final String KOREAN_NAME = "딜러";

    private static Dealer dealer_instance;

    private Dealer() {
        this.name = KOREAN_NAME;
    }

    public static Dealer getDealer() {
        if (dealer_instance == null) {
            dealer_instance = new Dealer();
        }
        return dealer_instance;
    }

    public boolean isUnderCriticalScore() {
        return calculateScore() <= DEALER_CRITICAL_SCORE;
    }
}