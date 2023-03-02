package domain;

import java.util.ArrayList;
import java.util.List;

public class DrawnCards {

    private static final int BURST_NUMBER = 21;

    private final List<Card> cards;

    public DrawnCards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (sum > BURST_NUMBER) {
            sum = calculateAce(sum);
        }

        return sum;
    }

    private int calculateAce(int sum) {
        long countOfAce = cards.stream()
                .filter(Card::isAce)
                .count();

        while (countOfAce-- > 0 && sum > BURST_NUMBER) {
            sum -= 10;
        }
        return sum;
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
