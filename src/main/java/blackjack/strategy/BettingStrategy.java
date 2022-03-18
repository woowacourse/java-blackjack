package blackjack.strategy;

@FunctionalInterface
public interface BettingStrategy {

    int getBettingAmount(String playerName);
}
