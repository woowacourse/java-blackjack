package domain.card;

import java.util.HashSet;
import java.util.Set;

public final class RandomUniqueCardSelector implements CardSelector {

    private final Set<Integer> beforeOrder;

    public RandomUniqueCardSelector() {
        this.beforeOrder = new HashSet<>();
    }

    @Override
    public int selectCardOrder(final int cardSize) {
        int cardOrder = calculateRandomCardOrder(cardSize);

        while (!beforeOrder.add(cardOrder)) {
            cardOrder = calculateRandomCardOrder(cardSize);
        }

        return cardOrder;
    }

    private int calculateRandomCardOrder(final int cardSize) {
        return (int) (Math.random() * cardSize);
    }
}
