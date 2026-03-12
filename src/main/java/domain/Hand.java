package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int BLACK_JACK = 21;
    public static final String ERROR_CARD_NOT_EXIST = "[ERROR] 카드가 존재하지 않습니다.";

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public boolean isBust() {
        return score() > BLACK_JACK;
    }

    public int score() {
        int total = calculateRawTotal();
        int aceCount = countAce();

        while (total > BLACK_JACK && aceCount > 0) {
            total -= 10;
            aceCount--;
        }

        return total;
    }

    public Card firstCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ERROR_CARD_NOT_EXIST);
        }
        return cards.getFirst();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public int size() {
        return cards.size();
    }

    private int calculateRawTotal() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getAllCards() {
        return List.copyOf(cards);
    }
}
