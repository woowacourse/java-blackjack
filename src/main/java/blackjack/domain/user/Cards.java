package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards implements Comparable<Cards> {
    private static final int FIRST_CARD = 0;
    private static final int BUST = 21;

    private final List<Card> cards;

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
        int totalValue = cards.stream()
                .mapToInt(Card::value)
                .sum();
        if (this.isSoftHand() && totalValue > BUST) {
            totalValue -= 10;
        }
        return totalValue;
    }

    public boolean isSoftHand() {
        return cards.stream()
                .anyMatch(Card::isAceCard);
    }

    public void combine(Cards otherCards) {
        this.cards.addAll(otherCards.getCards());
    }

    public boolean isBust() {
        return calculateTotalValue() > BUST;
    }

    @Override
    public int compareTo(Cards otherCards) {
        return Integer.compare(this.calculateTotalValue(), otherCards.calculateTotalValue());
    }
}
