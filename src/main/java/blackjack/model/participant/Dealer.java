package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int PICK_THRESHOLD = 16;

    public Dealer() {
        super(DEALER_NAME, Hands.empty());
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        
        Card secondPickedCard = pickSecondCard(cardDeck);
        hands.addCard(secondPickedCard);
    }
    
    private Card pickSecondCard(CardDeck cardDeck) {
        Card secondPickedCard = cardDeck.pick();
        secondPickedCard.flip();
        return secondPickedCard;
    }

    public boolean canPick() {
        return !hands.isTotalScoreOver(PICK_THRESHOLD);
    }
}
