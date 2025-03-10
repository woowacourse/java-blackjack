package domain.participant;

import domain.card.Card;
import domain.card.GameCardDeck;
import domain.card.ParticipantCardDeck;
import java.util.List;

public abstract class Participant {

    protected final ParticipantCardDeck cardDeck;

    protected Participant() {
        this.cardDeck = ParticipantCardDeck.generateEmptySet();
    }

    public abstract boolean ableToDraw();
    public abstract boolean areYouDealer();
    public abstract String getNickname();

    public void drawCard(GameCardDeck gameCardDeck, int count) {
        List<Card> drawedCard = gameCardDeck.draw(count);
        for (Card card : drawedCard) {
            cardDeck.addCard(card);
        }
    }

    public int getScore() {
        return cardDeck.calculateScore();
    }

    public ParticipantCardDeck getCardDeck() {
        return new ParticipantCardDeck(cardDeck);
    }
}
