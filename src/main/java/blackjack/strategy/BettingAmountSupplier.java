package blackjack.strategy;

@FunctionalInterface
public interface BettingAmountSupplier {

    int getBettingAmount();
}
