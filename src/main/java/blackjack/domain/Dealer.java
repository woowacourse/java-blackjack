package blackjack.domain;

public class Dealer extends User {

    public static final String DEALER = "딜러";
    public static final int THRESHOLD = 16;

    public Dealer(UserCards userCards) {
        super(DEALER, userCards);
    }

    public String showDealerCardInfo() {
        return cards.getCardInfo().get(0);
    }

    public boolean isUnderThreshold() {
        return cards.getTotalScore() <= THRESHOLD;
    }
}
