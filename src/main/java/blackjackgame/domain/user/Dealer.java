package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public class Dealer extends User {
    private static final Name DEALER_NAME = new Name("딜러");

    private DealerStatus status = DealerStatus.UNDER_MIN_SCORE;

    public Dealer() {
        super(DEALER_NAME);
    }


    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = hands.calculateDealerStatus();
    }

    @Override
    public void receiveCards(List<Card> cards) {
        super.receiveCards(cards);
        status = hands.calculateDealerStatus();
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
        return DEALER_NAME.getName();
    }
}
