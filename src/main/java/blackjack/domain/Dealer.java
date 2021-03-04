package blackjack.domain;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int STANDARD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDrawable() {
        return cards.calculateTotalValue() <= STANDARD;
    }

    public Card showOneCard() {
        return cards.oneCard();
    }
}
