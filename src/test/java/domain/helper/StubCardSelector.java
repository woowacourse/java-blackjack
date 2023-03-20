package domain.helper;

import domain.card.CardSelector;
import java.util.List;

public class StubCardSelector implements CardSelector {

    private final List<Integer> cardOrders;
    private int index;

    public StubCardSelector(final List<Integer> cardOrders) {
        this.cardOrders = cardOrders;
        this.index = 0;
    }

    @Override
    public int selectCardOrder(final int cardSize) {
        if (index == cardSize) {
            index = 0;
        }
        return cardOrders.get(index++);
    }
}
