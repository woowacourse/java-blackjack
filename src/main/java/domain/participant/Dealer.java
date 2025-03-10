package domain.participant;

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
}
