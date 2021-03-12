package blackjack.domain.card;

import blackjack.domain.card.Card;

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

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int sumCards() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int sumCardsForResult() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum() + aceCount * ACE_DIFFERENCE;
        while (sum > BLACKJACK && aceCount > 0) {
            sum -= ACE_DIFFERENCE;
            aceCount--;
        }
        return sum;
    }

    public boolean isBlackjack() {
        return sumCardsForResult() == BLACKJACK && cards.size() == 2;
    }

    public boolean isBust() {
        return sumCards() > BLACKJACK;
    }

    public boolean isHit() {
        return !isBlackjack() && !isBust();
    }
}
