package domain.area;

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

    public int calculate() {
        int aceCount = (int) cards.stream().filter(it -> it.cardValue().isAce()).count();
        int total = cards.stream().mapToInt(it -> it.cardValue().value()).sum();
        while (aceCount > 0) {
            if (total <= 11) {
                total += 10;
            }
            aceCount--;
        }
        return total;
    }

    public boolean canMoreCard() {
        return calculate() < 21;
    }

    public boolean isBurst() {
        return calculate() > 21;
    }
}
