package domain;

import java.util.List;
import java.util.Random;

public class Participant {

    protected final Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public List<Card> draw() {
        if (cards.canDraw()) {
            cards.add(new Card(new Random().nextInt(10) + 1));
            return cards.getCards();
        }
        return cards.getCards();
    }
}
