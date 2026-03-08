package blackjack.model;

public class ResultJudgement {

    private final BustPolicy bustPolicy;

    public ResultJudgement(BustPolicy bustPolicy) {
        this.bustPolicy = bustPolicy;
    }

    public PlayerBlackjackResult judge(Score playerScore, Score dealerScore) {
        if (isBust(playerScore)) {
            return PlayerBlackjackResult.LOSE;
        }
        if (isBust(dealerScore)) {
            return PlayerBlackjackResult.WIN;
        }
        if (playerScore.value() > dealerScore.value()) {
            return PlayerBlackjackResult.WIN;
        }
        if (playerScore.value() == dealerScore.value()) {
            return PlayerBlackjackResult.PUSH;
        }
        return PlayerBlackjackResult.LOSE;
    }

    private boolean isBust(Score playerScore) {
        return bustPolicy.isBust(playerScore);
    }
}
