package blackjack;

import blackjack.cards.Cards;
import java.util.List;

public class Gamer extends Player {

    public Gamer(String name, Card card1, Card card2, Card... cards) {
        super(name, List.of(card1, card2), Cards.mixHandCards(card1, card2, cards));
    }

    public boolean isHittable() {
        return score().lessThan(new Score(21));
    }
}
