package blackjack.domain.player;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    public static final int HIT_THRESHOLD = 16;

    public Dealer(Hand hand) {
        super(new PlayerName(DEALER_NAME), hand);
    }

    @Override
    public boolean canHit() {
        return hand.calculateCardSummation() <= HIT_THRESHOLD;
    }

    public void completeHand(CardDeck cardDeck) {
        while (canHit()) {
            appendCard(cardDeck.popCard());
        }
    }
}
