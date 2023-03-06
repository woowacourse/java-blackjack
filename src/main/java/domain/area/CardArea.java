package domain.area;

import domain.Score;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardArea {

    private final List<Card> cards = new ArrayList<>();

    public CardArea(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

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
            if (totalValue <= 11) {
                totalValue += 10;
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
