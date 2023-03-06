package domain.area;

import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardArea {

    private static final int REMAIN_SCORE_ACE = 10;
    private static final int ADD_ACE_BUST_OR_NOT = 11;

    private final List<Card> cards = new ArrayList<>();

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score calculate() {
        int aceCount = countAceCard();
        int totalValue = sumTotalCardValue();

        while (aceCount > 0) {
            if (totalValue <= ADD_ACE_BUST_OR_NOT) {
                totalValue += REMAIN_SCORE_ACE;
            }
            aceCount--;
        }
        return new Score(totalValue);
    }

    private int sumTotalCardValue() {
        return cards.stream()
                    .mapToInt(card -> card.cardValue().value())
                    .sum();
    }

    private int countAceCard() {
        return (int) cards.stream()
                          .filter(card -> card.cardValue().isAce())
                          .count();
    }

    public boolean canMoreCard() {
        return calculate().canMoreCard();
    }

    public boolean isBust() {
        return calculate().isBust();
    }

    public Card firstCard() {
        return cards.get(0);
    }
}
