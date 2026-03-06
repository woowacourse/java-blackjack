package blackjack.model;

public class ResultJudgement {

    private final BustPolicy bustPolicy;

    public ResultJudgement(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public BlackjackResult judge(int playerScore, int dealerScore) {
        if (bustPolicy.isBust(playerScore)) {
            return BlackjackResult.LOSE;
        }

        if (bustPolicy.isBust(dealerScore)) {
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
}
