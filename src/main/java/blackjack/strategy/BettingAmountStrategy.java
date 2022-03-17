package blackjack.strategy;

@FunctionalInterface
public interface BettingAmountStrategy {

    int getBettingAmount(String playerName);
}
