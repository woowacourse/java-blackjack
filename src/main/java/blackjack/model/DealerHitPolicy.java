package blackjack.model;

public interface DealerHitPolicy {
    boolean shouldHit(Score score);
}
