package blackjack.model;

public class ResultJudgement {

    private static final int BUST_LOWER_BOUND = 22;

    public BlackjackResult judge(int playerScore, int dealerScore) {
        if (isBust(playerScore)) {
            return BlackjackResult.LOSE;
        }

        if (isBust(dealerScore)) {
            return BlackjackResult.WIN;
        }

        if (playerScore > dealerScore) {
            return BlackjackResult.WIN;
        }

        if (playerScore == dealerScore) {
            return BlackjackResult.PUSH;
        }

        return BlackjackResult.LOSE;
    }

    private boolean isBust(int score) {
        return score >= BUST_LOWER_BOUND;
    }
}
