package blackjack.domain.result;

public class Lose implements Result {

    @Override
    public int calculateBet(final int amount) {
        return -amount;
    }
}
