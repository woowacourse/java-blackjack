package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Participant;

public enum WinStatus {

    WIN,
    LOSE;

    public static WinStatus of(Participant participant, Dealer dealer) {
        if (!participant.canHit()) {
            return LOSE;
        }
        if (!dealer.canHit()) {
            return WIN;
        }
        return isWinnerWhenNotBust(participant, dealer);
    }

    private static WinStatus isWinnerWhenNotBust(Participant participant, Dealer dealer) {
        int participantScore = participant.getScore();
        int dealerScore = dealer.getScore();
        if (participantScore == dealerScore) {
            return isWinnerByCardCount(participant, dealer);
        }
        if (participantScore > dealerScore) {
            return WIN;
        }
        return LOSE;
    }

    private static WinStatus isWinnerByCardCount(Participant participant, Dealer dealer) {
        if (participant.getCardCount() < dealer.getCardCount()) {
            return WIN;
        }
        return LOSE;
    }
}
