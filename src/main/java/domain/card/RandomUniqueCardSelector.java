package domain.card;

import domain.CardSelector;
import java.util.HashSet;
import java.util.Set;

public class RandomUniqueCardSelector implements CardSelector {

    private final Set<Integer> beforeOrder;

    public RandomUniqueCardSelector() {
        this.beforeOrder = new HashSet<>();
    }

    @Override
    public int selectCardOrder(final int cardSize) {
        int cardOrder = (int) (Math.random() * cardSize);

        while (!beforeOrder.add(cardOrder)) {
            cardOrder = (int) (Math.random() * cardSize);
        }

        return cardOrder;
    }
}
