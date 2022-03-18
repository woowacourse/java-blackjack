package domain.participant;

import domain.GameResult;
import domain.HitThreshold;
import domain.card.Card;

public final class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_INDEX = 0;

    public Dealer() {
        super(HitThreshold.DEALER_THRESHOLD, DEALER_NAME);
    }

    public Card getCard() {
        return cards.getCards().get(FIRST_INDEX);
    }

    public int getGameProfit(final Player player) {
        return GameResult.calculateDealerProfit(this, player);
    }
}
