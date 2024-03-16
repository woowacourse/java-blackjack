package model.participant;

import static model.participant.MatchState.DRAW;
import static model.participant.MatchState.LOSE;
import static model.participant.MatchState.WIN;

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

    public MatchState determineMatchResult(MatchState matchState) {
        if (matchState == LOSE) {
            return WIN;
        }
        if (matchState == WIN) {
            return LOSE;
        }
        return DRAW;
    }
}
