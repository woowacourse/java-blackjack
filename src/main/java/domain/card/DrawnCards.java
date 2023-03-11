package domain.card;

import java.util.ArrayList;
import java.util.List;

public class DrawnCards {

    private static final int GAP_OF_ACE_NUMBER = 10;
    private static final int BUST_NUMBER = 21;
    private static final int SIZE_OF_BLACK_JACK_COUNT = 2;

    private final List<Card> cards;

    public DrawnCards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if (sum > BUST_NUMBER) {
            sum = calculateAce(sum);
        }

        return sum;
    }

    private int calculateAce(int sum) {
        long countOfAce = cards.stream()
                .filter(Card::isAce)
                .count();

        while (countOfAce-- > 0 && sum > BUST_NUMBER) {
            sum -= GAP_OF_ACE_NUMBER;
        }
        return sum;
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isCardsSizeBlackjack() {
        return cards.size() == SIZE_OF_BLACK_JACK_COUNT;
    }
}
