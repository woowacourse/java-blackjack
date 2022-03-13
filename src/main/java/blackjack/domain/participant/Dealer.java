package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_MIN_TOTAL = 17;
    private static final String DEALER = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER, cards);
    }

    public boolean isFinished() {
        return holdingCard.calculateTotal() >= DEALER_MIN_TOTAL;
    }

}
