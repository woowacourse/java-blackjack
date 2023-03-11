package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
            sum -= CardValue.ACE.getScore() - CardValue.ACE.getExtraScore();
        }
        return sum;
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }

        return calculateScore() == BURST_NUMBER;
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public List<Card> getCards() {
        return cards;
    }
}
