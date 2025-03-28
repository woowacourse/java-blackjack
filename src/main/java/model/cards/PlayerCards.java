package model.cards;

import java.util.List;
import model.card.Card;

final public class PlayerCards extends Cards {
    public PlayerCards(final List<Card> cards) {
        super(cards);
    }

    public boolean canDraw() {
        return super.calculateSum() < MAX_SCORE;
    }
}
