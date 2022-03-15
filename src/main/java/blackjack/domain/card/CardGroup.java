package blackjack.domain.card;

import blackjack.domain.card.property.CardNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardGroup {
    public static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_SPECIAL_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_NUMBER;
    }

    public boolean isAddable() {
        return calculateScore() < BLACKJACK_NUMBER;
    }

    public int calculateSumWithMaximumAce() {
        int sum = cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getNumber)
                .sum();
        return sum + countA() * ACE_SPECIAL_SCORE;
    }

    public int calculateScore() {
        int maxSum = calculateSumWithMaximumAce();
        int aCount = countA();
        while (maxSum > BLACKJACK_NUMBER && aCount > 0) {
            maxSum -= ACE_SPECIAL_SCORE;
            aCount--;
        }
        return maxSum;
    }

    private int countA() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public void open() {
        for (Card card : cards) {
            card.open();
        }
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
