package blackjack.domain.status;

import blackjack.domain.Game;
import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    public static final int ZERO = 0;
    public static final int DIFFERENCE_OF_ACE_VALUE = 10;
    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateResult() {
        int currentCardsValue = sumCardsValue();
        int remainAceCount = countAce();

        while (canLowerCardsValue(currentCardsValue, remainAceCount)) {
            currentCardsValue = lowerValueOfAce(currentCardsValue);
            remainAceCount--;
        }

        return currentCardsValue;
    }

    private boolean canLowerCardsValue(int value, int aceCount) {
        return value > Game.BLACKJACK_NUMBER && aceCount > ZERO;
    }

    private int lowerValueOfAce(int value) {
        return value - DIFFERENCE_OF_ACE_VALUE;
    }

    public int countAce() {
        return (int) cards.stream()
                          .filter(Card::isAce)
                          .count();
    }

    public int sumCardsValue() {
        return cards.stream()
                    .mapToInt(Card::getValue)
                    .sum();
    }

    public boolean isBlackJack() {
        return calculateResult() == Game.BLACKJACK_NUMBER;
    }

    public boolean isBust() {
        return calculateResult() > Game.BLACKJACK_NUMBER;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> subList(int fromIndex, int toIndex) {
        return new ArrayList<>(cards.subList(fromIndex, toIndex));
    }
}
