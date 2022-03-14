package blackjack.domain.result;

public class Lose implements Result {

    @Override
    public int calculateBet(final int amount) {
        if (amount < Draw.DEFAULT_BET) {
            return amount;
        }
        return -amount;
    }
}
