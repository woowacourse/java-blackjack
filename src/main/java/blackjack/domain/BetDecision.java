package blackjack.domain;

@FunctionalInterface
public interface BetDecision {
    String decideBet(String name);
}
