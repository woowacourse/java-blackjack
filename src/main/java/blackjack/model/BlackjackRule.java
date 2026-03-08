package blackjack.model;

public class BlackjackRule {
    private final PolicyManager policyManager;
    private final ResultJudgement resultJudgement;

    public BlackjackRule(PolicyManager policyManager, ResultJudgement resultJudgement) {
        this.policyManager = policyManager;
        this.resultJudgement = resultJudgement;
    }

    public boolean canHitPlayer(Score score) {
        return policyManager.canHitPlayer(score);
    }

    public boolean shouldHitDealer(Score score) {
        return policyManager.shouldHitDealer(score);
    }

    public PlayerBlackjackResult judge(Score playerScore, Score dealerScore) {
        return resultJudgement.judge(playerScore, dealerScore);
    }
}
