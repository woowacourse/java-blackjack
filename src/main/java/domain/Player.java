package domain;

import java.util.List;
import java.util.Random;

public class Player {

    private final Name name;
    private final PlayerCards cards;

    public Player(Name name, PlayerCards cards) {
        this.name = name;
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
