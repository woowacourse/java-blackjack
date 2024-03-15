package model.participant;

import static model.participant.MatchResult.DRAW;
import static model.participant.MatchResult.LOSE;
import static model.participant.MatchResult.WIN;

import model.card.Card;

public final class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(final Card firstCard, final Card secondCard) {
        super(firstCard, secondCard);
    }

    @Override
    public boolean canHit() {
        return cardDeck.calculateHand() <= HIT_THRESHOLD;
    }

    public MatchResult determineMatchResult(MatchResult matchResult) {
        if (matchResult == LOSE) {
            return WIN;
        }
        if (matchResult == WIN) {
            return LOSE;
        }
        return DRAW;
    }
}
