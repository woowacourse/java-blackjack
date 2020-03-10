package domain;

import java.util.List;

public class Cards {
    private List<Card> cards;

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }
}
