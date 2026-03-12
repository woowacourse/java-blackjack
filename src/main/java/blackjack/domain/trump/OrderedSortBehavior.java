package blackjack.domain.trump;

import java.util.List;
import java.util.stream.IntStream;

public class OrderedSortBehavior implements SortBehavior {

    private final List<Card> order;

    public OrderedSortBehavior(final List<Card> order) {
        this.order = order;
    }


    @Override
    public void sort(final List<Card> cards) {
        IntStream.range(0, cards.size())
            .filter(index -> order.contains(cards.get(index)))
            .forEach(cards::remove);
        cards.addAll(order.reversed());
    }
}
