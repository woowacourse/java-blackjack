package blackjack.model;

import java.util.List;

public class Gamer extends Player {

    public Gamer(String name, Card card1, Card card2, Card... cards) {
        super(name, List.of(card1, card2), new Cards(card1, card2, cards));
    }

    public boolean isHittable() {
        return score().lessThan(new Score(21));
    }
}
