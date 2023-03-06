package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BOUNDARY = 21;
    private static final int BIGGER_A_SCORE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScore() {
        int sum = 0;

        for (Card card : cards) {
            sum += card.getScore();
        }
        if (notBustedWhenHasAce(sum + BIGGER_A_SCORE)) {
            sum += BIGGER_A_SCORE;
        }

        return sum;
    }

    private boolean notBustedWhenHasAce(int sum) {
        return isContainAce() && !isBusted(sum);
    }

    public boolean isBusted(int sum) {
        return sum > BOUNDARY;
    }

    private boolean isContainAce() {
        return cards.stream()
                .map(Card::getName)
                .anyMatch(name -> name.equals(Denomination.ACE.getName()));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
