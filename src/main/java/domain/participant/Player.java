package domain.participant;

import domain.area.CardArea;
import domain.card.Card;

public abstract class Player {

    protected final Name name;
    protected final CardArea cardArea;

    protected Player(final Name name, final CardArea cardArea) {
        this.name = name;
        this.cardArea = cardArea;
    }

    public void hit(final Card card) {
        cardArea.addCard(card);
    }

    public abstract boolean canHit();
}
