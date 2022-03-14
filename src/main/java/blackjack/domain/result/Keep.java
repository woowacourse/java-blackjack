package blackjack.domain.result;

public class Keep implements Result {

    @Override
    public int calculateBet(int amount) {
        return amount;
    }
}
