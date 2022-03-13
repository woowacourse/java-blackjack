package blackjack.model.player;

import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Score;
import java.util.List;

public class Gamer extends Player {

    public Gamer(String name, List<Card> cards) {
        super(name, new Cards(cards));
    }

    @Override
    public List<Card> openCards() {
        return List.of(cards.getEachCard().get(0), cards.getEachCard().get(1));
    }

    public boolean isHittable() {
        return score().lessThan(new Score(21));
    }
}
