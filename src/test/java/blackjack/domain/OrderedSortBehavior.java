package blackjack.domain;

import blackjack.domain.trump.Card;
import blackjack.domain.trump.SortBehavior;
import java.util.List;
import java.util.stream.IntStream;

public class OrderedSortBehavior implements SortBehavior {

    private final List<Card> order;

    public OrderedSortBehavior(final List<Card> order) {
        this.order = order;
    }


    @Override
    public void sort(final List<Card> cards) {
        cards.removeAll(order);
        cards.addAll(order.reversed());
    }
}
