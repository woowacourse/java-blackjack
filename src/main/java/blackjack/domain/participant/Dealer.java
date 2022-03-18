package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.HashSet;
import java.util.Set;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";

    public Dealer(Set<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public Dealer() {
        this(new HashSet<>());
    }

    @Override
    public boolean hasNextTurn() {
        return new Score(this.getScore()).isPossibleDealerHit();
    }
}
