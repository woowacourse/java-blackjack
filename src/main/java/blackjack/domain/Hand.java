package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public int getSum() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public void add(final Card card) {
        cards.add(card);
    }
}
