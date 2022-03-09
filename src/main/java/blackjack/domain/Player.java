package blackjack.domain;

import java.util.List;

public class Player {

    private final String name;
    private final Cards cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }
}
