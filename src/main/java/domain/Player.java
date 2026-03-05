package domain;

import java.util.List;

public class Player {

    private List<Card> cards;

    private Player(List<Card> cards) {
        this.cards = cards;
    }

    public static Player of(List<Card> cards) {
        return new Player(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}
