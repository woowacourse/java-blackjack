package domain.participant;

import static domain.GameManager.DEALER_HIT_MIN_THRESHOLD;

import domain.card.Card;
import domain.card.CardDeck;

public class Dealer extends Participant {
    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        super();
        this.cardDeck = cardDeck;
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public Card drawCard() {
        return cardDeck.popCard();
    }

    public boolean passCardToSelf() {
        if (getScore() > DEALER_HIT_MIN_THRESHOLD) {
            return false;
        }
        receive(drawCard());
        return true;
    }
}
