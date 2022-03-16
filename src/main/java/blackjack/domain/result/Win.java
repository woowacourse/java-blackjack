package blackjack.domain.result;

public class Win implements ResultStrategy {

    @Override
    public int calculateBet(final int amount) {
        return amount;
    }
}
