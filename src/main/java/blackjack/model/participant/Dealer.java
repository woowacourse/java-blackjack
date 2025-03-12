package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.card.Card;
import java.util.ArrayList;

public class Dealer extends Participant {

    public Dealer() {
        super(new ArrayList<>());
    }

    public boolean shouldHit() {
        return calculateHandTotal() <= DEALER_HIT_THRESHOLD;
    }

    public Card getVisibleCard() {
        if (hand.isEmpty()) {
            throw new IllegalStateException("딜러가 가진 패가 없습니다.");
        }
        return hand.getFirst();
    }
}
