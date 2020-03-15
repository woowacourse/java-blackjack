package blackjack.domain.user;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER);
    }

    public String showInitialCardNames() {
        return name + CARD + cards.firstCard();
    }

    public boolean isUnderThreshold() {
        return cards.calculateTotalScore() <= THRESHOLD;
    }

    public int compareScoreWith(int playerTotalScore) {
        return this.getTotalScore() - playerTotalScore;
    }
}
