package model.participant;

import static model.participant.state.MatchState.BLACK_JACK;
import static model.participant.state.MatchState.DRAW;
import static model.participant.state.MatchState.LOSE;
import static model.participant.state.MatchState.WIN;

import model.card.Card;
import model.participant.state.MatchState;

public final class Player extends Participant {
    private final Name name;
    private final long bettingAmount;

    public Player(Name name, long bettingAmount, Card firstCard, Card secondCard) {
        super(firstCard, secondCard);
        this.name = name;
        this.bettingAmount = bettingAmount;
    }


    @Override
    public boolean canHit() {
        return !cardDeck.isBust() && matchState == MatchState.PLAYING;
    }

    public void turnOver() {
        matchState = MatchState.TURNOVER;
    }

    public void updateMatchResult(MatchState dealerMatchResult, int dealerHand) {
        matchState = determineMatchResult(dealerMatchResult, dealerHand);
    }

    private MatchState determineMatchResult(MatchState dealerMatchResult, int dealerHand) {
        int playerHand = cardDeck.calculateHand();
        if (((dealerHand < playerHand) || BUST_THRESHOLD < dealerHand) && (playerHand < BUST_THRESHOLD)) {
            return WIN;
        }
        if (dealerHand == playerHand || (dealerMatchResult == BLACK_JACK && matchState == BLACK_JACK)) {
            return DRAW;
        }
        return LOSE;
    }

    public Name getName() {
        return name;
    }

    public long calculateEarning() {
        return matchState.calculateEarnings(bettingAmount);
    }
}
