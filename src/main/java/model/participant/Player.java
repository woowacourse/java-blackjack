package model.participant;

import static model.participant.MatchState.DRAW;
import static model.participant.MatchState.LOSE;
import static model.participant.MatchState.WIN;

import model.card.Card;

public final class Player extends Participant {
    private final Name name;

    public Player(Name name, Card card1, Card card2) {
        super(card1, card2);
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && matchState == MatchState.PLAYING;
    }

    public MatchState calculateMatchResult(int dealerHand) {
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
        if (matchState == MatchState.PLAYING) {
            matchState = MatchState.TURNOVER;
        }
        throw new IllegalStateException("Only Playing State Player can be turned over");
    }

    public Name getName() {
        return name;
    }
}
