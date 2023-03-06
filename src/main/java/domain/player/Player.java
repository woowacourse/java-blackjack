package domain.player;

import domain.Score;
import domain.area.CardArea;
import domain.card.Card;

public abstract class Player {

    private final Name name;
    protected final CardArea cardArea;

    protected Player(final Name name) {
        this.name = name;
        cardArea = new CardArea();
    }

    public Name name() {
        return name;
    }

    public CardArea cardArea() {
        return cardArea;
    }

    public boolean isBust() {
        return cardArea.isBust();
    }

    public void hit(final Card card) {
        cardArea.addCard(card);
    }

    public abstract boolean canHit();

    public Score score() {
        return cardArea.calculate();
    }
}
