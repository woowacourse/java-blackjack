package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards getDefault() {
        List<Card> emptyCards = new ArrayList<>();
        return new Cards(emptyCards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int getSumOfScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
