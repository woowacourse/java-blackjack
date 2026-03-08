package blackjack.domain;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean winsAgainst(Player other) {
        if (other.isBurst()) {
            return true;
        }
        if (this.isBurst()) {
            return false;
        }

        return other.calculateCardsValue() < this.calculateCardsValue();
    }

}
