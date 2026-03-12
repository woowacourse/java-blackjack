package blackjack.domain.participant;

import static blackjack.util.constant.Constants.DEALER_NAME;
import static blackjack.util.constant.Constants.HIT_THRESHOLD;

public class Dealer extends User {

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean shouldDrawCard() {
        return calculateCardsValue() < HIT_THRESHOLD;
    }
}
