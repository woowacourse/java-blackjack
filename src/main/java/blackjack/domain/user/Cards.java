package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards implements Comparable<Cards> {
    private static final int FIRST_CARD = 0;
    private static final int BUST_VALUE = 21;
    private static final int TEN = 10;

    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getOneCard() {
        return cards.get(FIRST_CARD);
    }

    public int calculateTotalValue() {
        int totalValue = sum();
        int aceCount = getAceCount();
        for (int i = 0; i < aceCount; i++) {
            totalValue += decideAceValue(totalValue);
        }
        return totalValue;
    }

    private int sum() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int decideAceValue(int totalValue) {
        if (isSoftHand() && totalValue + TEN < BUST_VALUE) {
            return TEN;
        }
        return 0;
    }

    public boolean isSoftHand() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.getCards());
    }

    public boolean isBust() {
        return calculateTotalValue() > BUST_VALUE;
    }

    @Override
    public int compareTo(Cards otherCards) {
        return Integer.compare(this.calculateTotalValue(), otherCards.calculateTotalValue());
    }
}
