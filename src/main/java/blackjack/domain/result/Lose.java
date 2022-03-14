package blackjack.domain.result;

public class Lose implements Result {

    @Override
    public int calculateBet(final int amount) {
        if (amount < 0) {
            return amount;
        }
        return -amount;
    }
}
