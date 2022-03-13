package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_POINT_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> showLimitedCard(int size) {
        return Collections.unmodifiableList(cards.subList(0, size));
    }

    public int getScore() {
        if (isContainsAce(cards)) {
            return sumWithAce(cards);
        }

        return getSumPoint();
    }

    private boolean isContainsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.isAce());
    }

    private int sumWithAce(List<Card> cards) {
        int aceCount = Math.toIntExact(cards.stream()
                .filter(Card::isAce).count());

        int sum = cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
        
        return calculateWithAce(sum, aceCount);
    }

    private int calculateWithAce(int sum, int aceCount) {
        if (aceCount == 0) {
            return sum;
        }

        if (sum + ACE_POINT_DIFFERENCE <= BLACKJACK_NUMBER) {
            return calculateWithAce(sum + ACE_POINT_DIFFERENCE, aceCount - 1);
        }

        return sum;
    }

    public int getSumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
