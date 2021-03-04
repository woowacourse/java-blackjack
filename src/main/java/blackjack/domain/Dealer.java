package blackjack.domain;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int MAX_HIT_VALUE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isAbleHit() {
        return cards.calculateTotalValue() <= MAX_HIT_VALUE;
    }

    public Card showOneCard() {
        return cards.oneCard();
    }
}
