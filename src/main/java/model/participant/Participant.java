package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.casino.MatchResult;
import service.dto.FaceUpResult;

public abstract class Participant {

    protected static final int BUST_THRESHOLD = 22;
    private final Name name;
    protected final CardDeck cardDeck;

    protected Participant(Name name) {
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    public abstract boolean canHit();

    public abstract MatchResult calculateMatchResult(int opponentHand);

    public void hitCard(Card card) {
        cardDeck.addCard(card);
    }

    public FaceUpResult generateFaceUpResult() {
        return new FaceUpResult(name, cardDeck.getCards(), cardDeck.calculateHand());
    }

    public Name getName() {
        return name;
    }
}
