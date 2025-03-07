package blackjack.model;

@FunctionalInterface
public interface HitDecisionStrategy {

    boolean decideHit(String playerName);
}
