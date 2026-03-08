package blackjack.model;

public class BlackjackRule {
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;
    private final ResultJudgement resultJudgement;

    public BlackjackRule(DealerHitPolicy dealerHitPolicy, BustPolicy bustPolicy, ResultJudgement resultJudgement) {
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.resultJudgement = resultJudgement;
    }

    public boolean canHitPlayer(Score score) {
        return !bustPolicy.isBust(score);
    }

    public boolean shouldHitDealer(Score score) {
        return dealerHitPolicy.shouldHit(score);
    }

    public PlayerBlackjackResult judge(Score playerScore, Score dealerScore) {
        return resultJudgement.judge(playerScore, dealerScore);
    }
}
