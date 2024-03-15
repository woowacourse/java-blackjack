package model.participant;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardDeck;

public abstract class Participant {

    protected static final int BUST_THRESHOLD = 22;
    protected final CardDeck cardDeck;

    protected Participant(final Card firstCard, final Card secondCard) {
        this.cardDeck = new CardDeck();
        cardDeck.addCard(firstCard);
        cardDeck.addCard(secondCard);
    }

    public abstract boolean canHit();

    public abstract MatchResult calculateMatchResult(int opponentHand);

    public void hitCard(Card card) {
        cardDeck.addCard(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cardDeck.getCards());
    }
}
