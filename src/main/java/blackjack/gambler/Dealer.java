package blackjack.gambler;

import blackjack.card.Card;
import java.util.List;

public class Dealer extends Gambler {

    public static final int DEALER_HIT_THRESHOLD = 16;

    public Dealer() {
        super();
    }

    @Override
    public List<Card> openInitialCards() {
        return cards.openDealerInitialCards();
    }

    public boolean isSumUnderThreshold() {
        return sumCardScores() <= DEALER_HIT_THRESHOLD;
    }
}
