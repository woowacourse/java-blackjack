package blackjack.model.participant;

@FunctionalInterface
public interface HitDecisionStrategy {

    boolean decideHit(String playerName);
}
