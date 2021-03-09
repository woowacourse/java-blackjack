package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.exception.InvalidNameInputException;

public class Dealer extends BlackJackParticipant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void draw(Card card) {
        getHand().addCard(card);
        if (isOverLimit()) {
            cannotDraw();
        }
    }

    private boolean isOverLimit() {
        return getScore() > DEALER_LIMIT;
    }
}
