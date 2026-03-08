package blackjack.model;

public class PolicyManager {
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;

    public PolicyManager(DealerHitPolicy dealerHitPolicy, BustPolicy bustPolicy) {
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
    }

    public boolean canHitPlayer(Score score) {
        return !bustPolicy.isBust(score);
    }

    public boolean shouldHitDealer(Score score) {
        return dealerHitPolicy.shouldHit(score);
    }
}
