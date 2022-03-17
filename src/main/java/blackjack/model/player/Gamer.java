package blackjack.model.player;

import blackjack.model.Betting;
import blackjack.model.Card;
import blackjack.model.Cards;
import blackjack.model.Score;
import java.util.List;

public class Gamer extends Player {

    private final Betting betting;

    public Gamer(String name, List<Card> cards, Betting betting) {
        super(name, new Cards(cards));
        this.betting = betting;
    }

    public Betting getBetting() {
        return new Betting(betting.getAmount());
    }

    @Override
    public List<Card> openCards() {
        return List.of(cards.getEachCard().get(0), cards.getEachCard().get(1));
    }

    public boolean isHittable() {
        return score().lessThan(new Score(21));
    }
}
