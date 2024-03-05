package domain;

import java.util.List;
import java.util.Random;

public class Player {

    private final Cards cards;

    public Player(Cards cards) {
        this.cards = cards;
    }

    public List<Card> draw() {
        if (cards.isOverMaxScore()) {
            return cards.getCards();
        }
        cards.add(new Card(new Random().nextInt(10) + 1));
        return cards.getCards();
    }
}
