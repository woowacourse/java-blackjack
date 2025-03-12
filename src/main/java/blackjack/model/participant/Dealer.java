package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import java.util.ArrayList;

public class Dealer extends Participant {

    public Dealer() {
        super("딜러", new ArrayList<>());
    }

    @Override
    public boolean canHit() {
        return calculateHandTotal() <= DEALER_HIT_THRESHOLD;
    }
}
