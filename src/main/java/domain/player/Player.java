package domain.player;

import domain.Score;
import domain.area.CardArea;
import domain.card.Card;

public abstract class Player {

    protected final CardArea cardArea;
    private final Name name;

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

    public boolean isBlackjack() {
        return cardArea.isBlackjack();
    }
}
