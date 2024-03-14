package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends BlackjackGamer {

    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super(DEFAULT_DEALER_NAME);
    }

    public Dealer(List<Card> cards) {
        super(DEFAULT_DEALER_NAME, cards);
    }

    @Override
    public boolean canReceiveCard() {
        return !isBlackjack()
                && getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
    }
}
