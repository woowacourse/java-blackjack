package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BOUNDARY = 21;
    private static final int BIGGER_A_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score calculateScore() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (canUseBiggerAce(sum)) {
            sum += BIGGER_A_SCORE;
        }

        return Score.from(sum);
    }

    private boolean canUseBiggerAce(int sum) {
        return sum + BIGGER_A_SCORE <= BOUNDARY && isContainAce();
    }

    private boolean isContainAce() {
        return cards.stream()
                .map(Card::getName)
                .anyMatch(name -> name.equals(Denomination.ACE.getName()));
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
