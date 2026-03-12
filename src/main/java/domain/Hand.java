package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACKJACK_SCORE = 21;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public int getSum() {
        int sum = getInitSum();
        long aceCount = countAce();

        while (aceCount > 0 && sum > BLACKJACK_SCORE) {
            sum -= 10;
            aceCount--;
        }

        return sum;
    }

    private int getInitSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private long countAce() {
        return cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return getSum() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return getSum() == BLACKJACK_SCORE;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getFirst() {
        return cards.getFirst();
    }

    public int size() {
        return cards.size();
    }
}
