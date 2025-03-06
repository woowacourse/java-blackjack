package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int MAX_SUM_OF_CARDS = 21;
    private static final int LARGE_ACE_NUMBER = 11;
    private static final int SMALL_ACE_NUMBER = 1;
    private static final int CARD_COUNT_OF_BLACKJACK = 2;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int sum() {
        int sumWithoutAce = cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(card -> card.number().getNumber())
            .sum();
        int sumOfAce = getSumOfAce(sumWithoutAce);
        return sumWithoutAce + sumOfAce;
    }

    private int getSumOfAce(int sumWithoutAce) {
        int sumOfAce = 0;
        int aceCardCount = getAceCardCount();
        if (aceCardCount >= CARD_COUNT_OF_BLACKJACK) {
            sumOfAce += aceCardCount - SMALL_ACE_NUMBER;
        }
        if (aceCardCount >= SMALL_ACE_NUMBER) {
            sumOfAce += aceNumber(sumWithoutAce);
        }
        return sumOfAce;
    }

    private int getAceCardCount() {
        return (int)cards.stream()
            .filter(Card::isAce)
            .count();
    }

    private int aceNumber(int sum) {
        if (sum + LARGE_ACE_NUMBER > MAX_SUM_OF_CARDS) {
            return SMALL_ACE_NUMBER;
        }
        return LARGE_ACE_NUMBER;
    }

    public boolean isBlackjack() {
        if (cards.size() != CARD_COUNT_OF_BLACKJACK) {
            return false;
        }
        return sum() == MAX_SUM_OF_CARDS;
    }

    public boolean isBust() {
        return sum() > MAX_SUM_OF_CARDS;
    }

    public int count() {
        return cards.size();
    }
}

