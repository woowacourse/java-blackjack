package domain.player;

import domain.card.Card;
import domain.card.CardArea;

public abstract class Participant {

    protected final Name name;
    protected final CardArea cardArea;

    protected Participant(final Name name, final CardArea cardArea) {
        this.name = name;
        this.cardArea = cardArea;
    }

    public Name name() {
        return name;
    }

    public CardArea cardArea() {
        return cardArea;
    }

    public String nameValue() {
        return name.value();
    }

    public boolean isBurst() {
        return cardArea.isBurst();
    }

    public void hit(final Card card) {
        cardArea.addCard(card);
    }

    public abstract boolean canHit();

    public int score() {
        return cardArea.calculate();
    }
}
