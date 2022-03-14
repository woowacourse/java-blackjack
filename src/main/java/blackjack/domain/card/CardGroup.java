package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.property.CardNumber;

public class CardGroup {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int ACE_SPECIAL_SCORE = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getSum() > BLACKJACK_NUMBER;
    }

    public boolean isAddable() {
        return getSum() < BLACKJACK_NUMBER;
    }

    public int getDealerSum() {
        return getSum() + countA() * ACE_SPECIAL_SCORE;
    }

    private int getSum() {
        return cards.stream()
            .map(Card::getCardNumber)
            .mapToInt(CardNumber::getNumber)
            .sum();
    }

    public int getPlayerSum() {
        int maxSum = getDealerSum();
        int aCount = countA();
        while (maxSum > BLACKJACK_NUMBER && aCount > 0) {
            maxSum -= ACE_SPECIAL_SCORE;
            aCount--;
        }
        return maxSum;
    }

    private int countA() {
        return (int) cards.stream()
                .filter(Card::isA)
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
