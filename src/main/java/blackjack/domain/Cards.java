package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards implements Comparable<Cards> {
    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> cards(){
        return Collections.unmodifiableList(cards);
    }

    public Card oneCard() {
        return cards.get(FIRST_CARD);
    }

    public int calculateTotalValue() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.cards());
    }

    @Override
    public int compareTo(Cards otherCards) {
        return Integer.compare(this.calculateTotalValue(), otherCards.calculateTotalValue());
    }
}
