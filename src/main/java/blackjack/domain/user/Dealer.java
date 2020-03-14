package blackjack.domain.user;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER);
    }

    public String showInitialCardInfo() {
        return name + CARD + userCards.getCardInfo().get(0);
    }

    public boolean isUnderThreshold() {
        return userCards.getTotalScore() <= THRESHOLD;
    }
}
