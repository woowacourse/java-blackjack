package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.List;

public class FixedOrderShuffleStrategy implements ShuffleStrategy {

    private final List<Card> fixedOrder;

    public FixedOrderShuffleStrategy(List<Card> fixedOrder) {
        this.fixedOrder = fixedOrder;
    }

    @Override
    public void shuffle(List<Card> cards) {
        cards.clear();
        cards.addAll(fixedOrder);
    }
}
