package model.player;

import java.util.List;
import java.util.function.Supplier;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardSize;

public class Dealer extends User {

    private static final int NUMBER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck, Supplier<List<Card>> supplier) {
        super(DEALER_NAME, supplier.get());
        this.cardDeck = cardDeck;
    }

    public boolean isHit() {
        return calculateScore() <= NUMBER_THRESHOLD;
    }

    public List<Card> takeCardFromDeck(CardSize cardSize) {
        return cardDeck.selectRandomCards(cardSize);
    }

    public void offerCardToParticipant(Participant receiver, CardSize size) {
        receiver.addCards(cardDeck.selectRandomCards(size));
    }
}
