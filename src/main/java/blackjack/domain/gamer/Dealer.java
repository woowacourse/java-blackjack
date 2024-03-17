package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends BlackjackGamer {

    private static final String DEFAULT_DEALER_NAME = "딜러";

    public Dealer() {
        super();
    }

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canReceiveCard() {
        return !isBlackjack()
                && getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
    }

    @Override
    public String getName() {
        return DEFAULT_DEALER_NAME;
    }

    public Card getFirstCard() {
        List<Card> cards = hand.cards();
        return cards.get(0);
    }
}
