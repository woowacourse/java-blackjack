package blackjack.domain;

import java.util.List;

public class Dealer extends Human {

    public static final int RECEIVED_MAXIMUM = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean compare(final Player player) {
        return getTotal() >= player.getTotal();
    }

    public List<String> getInitCard() {
        return cards.getFirstCardName();
    }

    @Override
    public boolean canDraw() {
        return cards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
