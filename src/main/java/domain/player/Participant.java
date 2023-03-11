package domain.player;

import domain.card.BlackJackScore;
import domain.card.CardArea;
import domain.card.CardDeck;

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

    public boolean isBust() {
        return cardArea.isBust();
    }

    public abstract boolean canHit();

    public BlackJackScore score() {
        return cardArea.calculate();
    }

    protected boolean isLargerScoreThan(final Participant participant) {
        return score().isLargerThan(participant.score());
    }

    public abstract boolean hitOrStay(final CardDeck cardDeck);

    protected void hit(final CardDeck cardDeck) {
        cardArea.addCard(cardDeck.draw());
    }
}
