package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.casino.MatchResult;
import service.dto.FaceUpResult;

public abstract class Participant {

    protected static final int BUST_THRESHOLD = 22;
    protected final CardDeck cardDeck;

    protected Participant() {
        this.cardDeck = new CardDeck();
    }

    public abstract boolean canHit();

    public abstract MatchResult calculateMatchResult(int opponentHand);

    public void hitCard(Card card) {
        cardDeck.addCard(card);
    }
}
