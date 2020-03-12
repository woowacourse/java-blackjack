package blackjack.domain.user;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer() {
        super(DEALER);
    }

    public String showInitialCardInfo() {
        return name + CARD + cards.getCardInfo().get(0);
    }

    public boolean isUnderThreshold() {
        return cards.getTotalScore() <= THRESHOLD;
    }
}
