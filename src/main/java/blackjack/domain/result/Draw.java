package blackjack.domain.result;

public class Draw implements Result {

    @Override
    public int calculateBet(int amount) {
        return 0;
    }
}
