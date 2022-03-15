package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import java.util.List;

public class Dealer extends Participant {

    private static final int RECEIVABLE_SCORE_THRESHOLD = 17;
    private static final Name DEFAULT_NAME = new Name("딜러");

    private Dealer(Name name) {
        super(name);
    }

    public static Dealer createDefaultNameDealer() {
        return new Dealer(DEFAULT_NAME);
    }

    @Override
    public boolean isReceivable() {
        return calculateBestScore() < RECEIVABLE_SCORE_THRESHOLD;
    }
}
