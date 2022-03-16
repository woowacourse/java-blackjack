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

    public boolean isNotBust() {
        return calculateScore() < BLACKJACK_NUMBER;
    }

    public int calculateScore() {
        int score = calculateSumWithMaximumAce();
        int aCount = countAce();
        while (isReducingAceNumberBeneficial(score, aCount)) {
            score -= ACE_SPECIAL_SCORE;
            aCount--;
        }
        return score;
    }

    private int calculateSumWithMaximumAce() {
        int sum = cards.stream()
                .map(Card::getCardNumber)
                .mapToInt(CardNumber::getNumber)
                .sum();
        return sum + countAce() * ACE_SPECIAL_SCORE;
    }

    private boolean isReducingAceNumberBeneficial(int score, int aCount) {
        return score > BLACKJACK_NUMBER && aCount > 0;
    }

    private int countAce() {
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
