package blackjack.domain.result;

public class Keep implements ResultStrategy {

    @Override
    public int calculateBet(final int amount) {
        return amount;
    }
}
