package blackjack.domain.result;

public class Win implements Result {

    @Override
    public int calculateBet(int amount) {
        return amount;
    }
}
