package blackjack.domain;

@FunctionalInterface
public interface BetDecision {
    int decideBet(String name);
}
