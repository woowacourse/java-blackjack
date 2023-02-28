package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BOUNDARY = 21;
    private static final int BIGGER_A_SCORE = 10;
    private static final String ACE = "A";
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getScore();
        }
        if (isContainA() && notBusted(sum)) {
            sum += BIGGER_A_SCORE;
        }

        return sum;
    }

    private boolean notBusted(int sum) {
        return sum + BIGGER_A_SCORE <= BOUNDARY;
    }

    private boolean isContainA() {
        return cards.stream()
                .map(Card::getName)
                .anyMatch(name -> name.equals(ACE));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
