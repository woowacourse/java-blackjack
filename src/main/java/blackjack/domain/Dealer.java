package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public List<String> getInitCard() {
        return cards.getFirstCardName();
    }

    @Override
    public boolean canDraw() {
        return cards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
