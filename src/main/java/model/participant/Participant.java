package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.dto.GameCompletionResult;

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

    public GameCompletionResult generateFaceUpResult() {
        return new GameCompletionResult(name, cardDeck.getCards(), cardDeck.calculateHand());
    }

    public int getHand() {
        return cardDeck.calculateHand();
    }

    public boolean isBustHand() {
        return cardDeck.isBust();
    }

}
