package blackjack.domain.machine.result;

import blackjack.domain.machine.Score;

public class JudgeFactory {

    public static MatchResults matchResult(Score playerScore, Score dealerScore) {
        if (isBlackjack(playerScore, dealerScore)) {
            return new Blackjack();
        }
        if (isLose(playerScore, dealerScore)) {
            return new Lose();
        }
        if (isDraw(playerScore, dealerScore)) {
            return new Draw();
        }
        return new Win();
    }

    private static boolean isBlackjack(Score playerScore, Score dealerScore) {
        return playerScore.isBlackjackWin(dealerScore);
    }

    private static boolean isLose(Score playerScore, Score dealerScore) {
        return playerScore.isLose(dealerScore);
    }

    private static boolean isDraw(Score playerScore, Score dealerScore) {
        return playerScore.isDraw(dealerScore);
    }
}
