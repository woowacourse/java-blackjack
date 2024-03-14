package model.participant;

import static model.casino.MatchResult.DRAW;
import static model.casino.MatchResult.LOSE;
import static model.casino.MatchResult.WIN;

import model.casino.MatchResult;

public class Player extends Participant {
    private boolean isTurnOver;

    public Player(Name name) {
        super(name);
        isTurnOver = false;
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && !isTurnOver;
    }

    @Override
    public MatchResult calculateMatchResult(int dealerHand) {
        int playerHand = cardDeck.calculateHand();
        if (playerHand >= BUST_THRESHOLD || (dealerHand < BUST_THRESHOLD) && (playerHand < dealerHand)) {
            return LOSE;
        }
        if (dealerHand == playerHand) {
            return DRAW;
        }
        return WIN;
    }

    public void turnOver() {
        isTurnOver = true;
    }
}
