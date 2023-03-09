package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";

    private DealerStatus status = DealerStatus.UNDER_MIN_SCORE;

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculateDealerStatus();
    }

    @Override
    public void receiveCards(List<Card> receivedCards) {
        super.receiveCards(receivedCards);
        status = score.calculateDealerStatus();
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }

    public int getExtraDrawCount(int initialCardCount) {
        return getCards().size() - initialCardCount;
    }

    @Override
    public DealerStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
