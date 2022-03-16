package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Score;

import java.util.Set;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer(Set<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean hasNextTurn() {
        return new Score(this.getScore()).isPossibleDealerHit();
    }
}
