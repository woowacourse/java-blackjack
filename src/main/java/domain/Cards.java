package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    public static final int BUST_STANDARD = 21;
    public static final int SOFT_ACE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addOneCard(Card card) {
        cards.add(card);
    }

    public int sumCardScores() {
        int sum = calculateScore();
        for (int i = 0; i < countAces(); i++) {
            sum = processAce(sum);
        }
        return sum;
    }

    public Card drawOneCard() {
        return cards.removeLast();
    }

    private int calculateScore() {
        return cards.stream()
                .mapToInt(card -> card.getNumber().getValue())
                .sum();
    }

    private int countAces() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int processAce(int sum) {
        if (sum > BUST_STANDARD) {
            sum -= SOFT_ACE_DIFFERENCE;
        }
        return sum;
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
