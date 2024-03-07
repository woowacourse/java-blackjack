package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.dto.IndividualFaceUpResult;

public abstract class Participant {

    private final Name name;
    protected final CardDeck cardDeck;

    protected Participant(Name name) {
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    public abstract boolean canHit();

    public void hitCard(Card card) {
        cardDeck.addCard(card);
    }

    public IndividualFaceUpResult generateFaceUpResult() {
        return new IndividualFaceUpResult(name, cardDeck.getCards(), cardDeck.calculateHand());
    }

    public Name getName(){
        return name;
    }
}
