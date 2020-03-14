package blackjack.domain.user;

public class Dealer extends User {

    private static final String DEALER = "딜러";
    private static final int THRESHOLD = 16;

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
