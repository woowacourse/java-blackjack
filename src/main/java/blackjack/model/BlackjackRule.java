package blackjack.model;

public class BlackjackRule {
    private final ScoreCalculator scoreCalculator;
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;
    private final ResultJudgement resultJudgement;

    public BlackjackRule(ScoreCalculator scoreCalculator, DealerHitPolicy dealerHitPolicy, BustPolicy bustPolicy,
                         ResultJudgement resultJudgement) {
        this.scoreCalculator = scoreCalculator;
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.resultJudgement = resultJudgement;
    }

    public boolean canHit(Participant player) {
        return !bustPolicy.isBust(scoreCalculator.calculate(player.getCards()));
    }

    public boolean shouldHit(Dealer dealer) {
        Score score = scoreCalculator.calculate(dealer.getCards());
        return dealer.shouldHit(dealerHitPolicy, score);
    }

    public Score calculate(Participant participant) {
        return scoreCalculator.calculate(participant.getCards());
    }

    public BlackjackResult judge(Participant player, Dealer dealer) {
        return resultJudgement.judge(
            scoreCalculator.calculate(player.getCards()),
            scoreCalculator.calculate(dealer.getCards()));
    }
}
