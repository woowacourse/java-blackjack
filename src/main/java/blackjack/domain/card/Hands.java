package blackjack.domain.card;

import java.util.List;

public class Hands {

    private final Cards cards;

    public Hands() {
        cards = new Cards();
    }

    public void initialize(List<Card> initialCards) {
        cards.of(initialCards);
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculate() {
        return cards.calculate();
    }

    public List<Card> toList() {
        return cards.toList();
    }

    public List<Card> getCardsWithSize(int number) {
        return cards.getCardsWithSize(number);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean containsAce() {
        return cards.containsAce();
    }
}
