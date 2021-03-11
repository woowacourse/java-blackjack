package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBlackJack() {
        int sum = cards.stream()
                .mapToInt(card -> card.numberScore())
                .sum();
        return sum == 21;
    }
}
