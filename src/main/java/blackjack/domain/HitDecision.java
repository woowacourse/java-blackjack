package blackjack.domain;

@FunctionalInterface
public interface HitDecision {
    boolean wantsHit(String name);
}
