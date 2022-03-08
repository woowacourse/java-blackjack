package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;

    private Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }
    public static Player of(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getNumber();
        }
        return point;
    }
}
