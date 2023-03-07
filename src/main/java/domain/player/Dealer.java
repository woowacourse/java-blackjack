package domain.player;

import domain.card.Card;
import domain.card.CardArea;

public class Dealer extends Participant {

    private static final Name DEALER_NAME = Name.of("딜러");
    private static final int DEALER_SHOULD_HIT_INCLUDE_VALUE = 16;

    public Dealer(final CardArea cardArea) {
        super(DEALER_NAME, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.calculate() <= DEALER_SHOULD_HIT_INCLUDE_VALUE;
    }

    public Card firstCard() {
        return cardArea.firstCard();
    }

    public DealerCompeteResult compete(final Participant participant) {
        if (participant.isBurst()) {
            return DealerCompeteResult.WIN;
        }
        if (isBurst()) {
            return DealerCompeteResult.LOSE;
        }
        if (participant.score() > score()) {
            return DealerCompeteResult.LOSE;
        }
        if (participant.score() == score()) {
            return DealerCompeteResult.DRAW;
        }
        return DealerCompeteResult.WIN;
    }
}
