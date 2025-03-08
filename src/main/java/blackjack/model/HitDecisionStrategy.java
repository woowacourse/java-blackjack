package blackjack.model;

@FunctionalInterface
public interface HitDecisionStrategy {

    boolean decideHit(Name playerName);
}
