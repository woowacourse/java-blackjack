package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.participant.rule.DealerRule;
import blackjack.domain.participant.rule.DrawRule;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAWABLE_MAX_SCORE = 16;
    private static final int START_CARD_SIZE = 1;
    private static final DrawRule<Dealer> DEALER_RULE = new DealerRule();

    public Dealer() {
        super(Collections.emptyList());
    }

    public Dealer(List<Card> cards) {
        super(cards);
    }

    @Override
    protected int getMaxDrawableScore() {
        return DRAWABLE_MAX_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }

    @Override
    protected DrawRule<Dealer> getRules() {
        return DEALER_RULE;
    }
}
