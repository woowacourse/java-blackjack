package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACKJACK_SCORE = 21;
    public static final int ACE_SCORE_ADJUSTMENT = 10;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public int calculateScore() {
        int sum = calculateInitSum();
        long aceCount = countAce();

        while (aceCount > 0 && sum > BLACKJACK_SCORE) {
            sum -= ACE_SCORE_ADJUSTMENT;
            aceCount--;
        }

        return sum;
    }

    private int calculateInitSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int getSize() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
