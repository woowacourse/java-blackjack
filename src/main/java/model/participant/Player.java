package model.participant;

import static model.participant.state.MatchState.DRAW;
import static model.participant.state.MatchState.LOSE;
import static model.participant.state.MatchState.WIN;

import model.card.Card;
import model.participant.state.MatchState;

public final class Player extends Participant {
    private final Name name;
    private final int bettingAmount;

    public Player(Name name, int bettingAmount, Card firstCard, Card secondCard) {
        super(firstCard, secondCard);
        this.name = name;
        this.bettingAmount = bettingAmount;
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
