package domain.gamer;

import domain.card.Card;
import domain.card.cardDrawable.Hand;

import java.util.List;

public class Player extends AbstractGamer {
    public Player(String name) {
        super(name, new Hand());
    }

    @Override
    public boolean canDrawMore() {
        return this.isBurst();
    }

    @Override
    public List<Card> openInitialCards() {
        return super.openAllCards();
    }
}
