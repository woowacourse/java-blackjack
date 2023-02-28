package domain.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        addAllCards();
    }

    private void addAllCards() {
        cards.addAll(Arrays.asList(CloverCard.values()));
        cards.addAll(Arrays.asList(DiamondCard.values()));
        cards.addAll(Arrays.asList(HeartCard.values()));
        cards.addAll(Arrays.asList(SpadeCard.values()));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
