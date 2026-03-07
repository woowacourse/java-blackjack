package blackjack.model;

public interface DealerHitPolicy {
    boolean shouldHit(int score);
}
