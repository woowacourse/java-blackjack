package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME, Hands.createEmptyHand());
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        validateCardDeck(cardDeck);

        hands.addCard(cardDeck.pick());
        hands.addCard(pickSecondCard(cardDeck));
    }
    
    private Card pickSecondCard(CardDeck cardDeck) {
        Card card = cardDeck.pick();
        return card.close();
    }

    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }
}
