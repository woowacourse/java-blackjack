package domain.participant;

import domain.card.Card;
import domain.card.CardDeck;

public class Dealer extends Participant {

    public static final int DEALER_HIT_MIN_THRESHOLD = 16;

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

    @Override
    public boolean canHit() {
        return getScore() <= DEALER_HIT_MIN_THRESHOLD;
    }
}
