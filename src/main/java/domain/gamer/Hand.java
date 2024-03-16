package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int MAX_SUM = 21;
    private static final int BLACKJACK_CARD_COUNT_COND = 2;
    private static final int DETERMINING_ACE_VALUE_COND = 11;
    private static final int ANOTHER_ACE_SCORE_DIFF = 10;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return sum() > MAX_SUM;
    }

    public boolean isBlackJack() {
        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return aceCount == 1 && cards.size() == BLACKJACK_CARD_COUNT_COND;
    }

    public int sum() {
        int totalCount = calculateTotalCount();
        if (hasAce() && totalCount <= DETERMINING_ACE_VALUE_COND) {
            return totalCount + ANOTHER_ACE_SCORE_DIFF;
        }
        return totalCount;
    }

    private int calculateTotalCount() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
