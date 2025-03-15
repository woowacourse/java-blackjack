package participant;

import card.Card;
import card.GameCardDeck;
import card.ParticipantCardDeck;
import game.GameRule;
import java.util.List;

public abstract class Participant {

    protected final ParticipantCardDeck cardDeck;

    protected Participant() {
        this.cardDeck = ParticipantCardDeck.generateEmptySet();
    }

    public abstract boolean ableToDraw();
    public abstract boolean isDealer();
    public abstract String getNickname();

    public boolean isBlackJack() {
        int cardSize = cardDeck.requestSize();
        int cardDeckScore = cardDeck.calculateScore();
        return cardSize == 2 && cardDeckScore == GameRule.BLACK_JACK.getValue();
    }

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
