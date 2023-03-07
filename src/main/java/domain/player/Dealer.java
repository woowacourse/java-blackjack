package domain.player;

import domain.card.Card;
import domain.card.CardArea;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return score().isDealerShouldHitScore();
    }

    public Card firstCard() {
        return cardArea.firstCard();
    }

    public DealerCompeteResult compete(final Participant participant) {
        if (participant.isBust()) {
            return DealerCompeteResult.WIN;
        }
        if (isBust()) {
            return DealerCompeteResult.LOSE;
        }
        if (participant.isLargerScoreThan(this)) {
            return DealerCompeteResult.LOSE;
        }
        if (isLargerScoreThan(participant)) {
            return DealerCompeteResult.WIN;
        }
        return DealerCompeteResult.DRAW;
    }
}
