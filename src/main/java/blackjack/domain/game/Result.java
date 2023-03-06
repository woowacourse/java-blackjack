package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_CHECK_SUM = 21;

    public static Result calculate(Participant targetParticipant, Participant oppositeParticipant) {
        int targetScore = calculateFinalScore(targetParticipant.calculateScore());
        int oppositeScore = calculateFinalScore(oppositeParticipant.calculateScore());
        if (targetScore > oppositeScore) {
            return WIN;
        }
        if (targetScore == oppositeScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static int calculateFinalScore(int sum) {
        if (sum > BLACKJACK_CHECK_SUM) {
            sum = 0;
        }
        return sum;
    }
}
