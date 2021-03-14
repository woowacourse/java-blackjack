package blackjack.domain.user;

public class Dealer extends User {

    public static final int DEALER_HIT_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_CARD_INDEX = 0;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean canHit() {
        return getScore().isLowerThan(DEALER_HIT_THRESHOLD);
    }

    public String showFirstCard() {
        return name.getName() + CARDS_GUIDE_MESSAGE + this.getFirstCard();
    }

    public String getFirstCard() {
        return cards.getCards()
            .get(FIRST_CARD_INDEX)
            .getName();
    }
}
