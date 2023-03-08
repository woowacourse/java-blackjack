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

    public GamblerCompeteResult compete(final Participant participant) {
        if (participant.isBust()) {
            return GamblerCompeteResult.LOSE;
        }
        if (isBust()) {
            return GamblerCompeteResult.WIN;
        }
        if (participant.isLargerScoreThan(this)) {
            return GamblerCompeteResult.WIN;
        }
        if (isLargerScoreThan(participant)) {
            return GamblerCompeteResult.LOSE;
        }
        return GamblerCompeteResult.DRAW;
    }
}
