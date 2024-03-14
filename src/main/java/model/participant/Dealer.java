package model.participant;

import static model.casino.MatchResult.DRAW;
import static model.casino.MatchResult.LOSE;
import static model.casino.MatchResult.WIN;

import model.casino.MatchResult;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean canHit() {
        return cardDeck.calculateHand() <= HIT_THRESHOLD;
    }

    @Override
    public MatchResult calculateMatchResult(int playerHand) {
        int dealerHand = cardDeck.calculateHand();
        if (playerHand >= BUST_THRESHOLD || (dealerHand < BUST_THRESHOLD) && (playerHand < dealerHand)) {
            return WIN;
        }
        if (dealerHand == playerHand) {
            return DRAW;
        }
        return LOSE;
    }
}
