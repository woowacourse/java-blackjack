package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int INITIAL_CARDS_SIZE = 2;
    public static final int BLACKJACK = 21;
    public static final int ACE_DIFFERENCE = 10;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException();
        }
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getUnmodifiableList() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> getHalfUnmodifiableList() {
        List<Card> halfCards = new ArrayList<>(cards.subList(0, cards.size() / 2));
        return Collections.unmodifiableList(halfCards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int hardSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int softSum() {
        int numberOfAces = numberOfAces();
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum() + numberOfAces * ACE_DIFFERENCE;
        while (sum > BLACKJACK && numberOfAces > 0) {
            sum -= ACE_DIFFERENCE;
            numberOfAces--;
        }
        return sum;
    }

    private int numberOfAces() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBlackjack() {
        return softSum() == BLACKJACK && cards.size() == 2;
    }

    public boolean isBust() {
        return hardSum() > BLACKJACK;
    }
}
