package blackjack.domain.result;

public class Keep implements Result {

    @Override
    public int calculateBet(final int amount) {
        return amount;
    }
}
