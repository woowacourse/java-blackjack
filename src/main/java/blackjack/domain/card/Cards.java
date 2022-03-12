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

    public void add(Card drawCard) {
        cards.add(drawCard);
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> showLimitedCard(int size) {
        return Collections.unmodifiableList(cards.subList(0, size));
    }

    public int sumPoint() {
        int aceCount = countAce(cards);

        if (aceCount > 0) {
            return sumWithAce(aceCount, cards);
        }

        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int sumWithAce(int aceCount, List<Card> cards) {
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

    private int countAce(List<Card> cards) {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce).count());
    }

    public int getSumPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
