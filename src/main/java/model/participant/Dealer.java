package model.participant;

import static model.participant.MatchResult.DRAW;
import static model.participant.MatchResult.LOSE;
import static model.participant.MatchResult.WIN;

import model.card.Card;

public class Dealer extends Participant {
    private static final int HIT_THRESHOLD = 16;

    public Dealer(final Card firstCard, final Card secondCard) {
        super(firstCard, secondCard);
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
