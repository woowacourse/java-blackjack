package model.participant;

import java.util.Collections;
import java.util.List;
import model.card.Card;
import model.card.CardDeck;
import model.participant.state.MatchState;

public abstract class Participant {

    protected static final int BUST_THRESHOLD = 22;
    protected final CardDeck cardDeck;
    protected MatchState matchState;

    protected Participant(final Card firstCard, final Card secondCard) {
        this.cardDeck = new CardDeck();
        cardDeck.addCard(firstCard);
        cardDeck.addCard(secondCard);
        this.matchState = initMatchState();
    }

    protected MatchState initMatchState() {
        if (cardDeck.isBlackJack()) {
            return MatchState.BLACK_JACK;
        }
        return MatchState.PLAYING;
    }

    public abstract boolean canHit();

    public void hitCard(Card card) {
        cardDeck.addCard(card);
        if (cardDeck.isBust()) {
            matchState = MatchState.TURNOVER;
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cardDeck.getCards());
    }

    public int getHand() {
        return cardDeck.calculateHand();
    }
}
