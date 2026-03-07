package blackjack.model;

public class ResultJudgement {

    private final BustPolicy bustPolicy;

    public ResultJudgement(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public BlackjackResult judge(Score playerScore, Score dealerScore) {
        if (bustPolicy.isBust(playerScore)) {
            return BlackjackResult.LOSE;
        }

        if (bustPolicy.isBust(dealerScore)) {
            return BlackjackResult.WIN;
        }

        if (playerScore.value() > dealerScore.value()) {
            return BlackjackResult.WIN;
        }

        if (playerScore.value() == dealerScore.value()) {
            return BlackjackResult.PUSH;
        }

        return BlackjackResult.LOSE;
    }
}
