package model.participant;

import static model.participant.MatchResult.DRAW;
import static model.participant.MatchResult.LOSE;
import static model.participant.MatchResult.WIN;

import model.card.Card;

public class Player extends Participant {
    private final Name name;
    private boolean isTurnOver;

    public Player(Name name, Card card1, Card card2) {
        super(card1, card2);
        this.name = name;
        this.isTurnOver = false;
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && !isTurnOver;
    }

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

    public Name getName() {
        return name;
    }
}
