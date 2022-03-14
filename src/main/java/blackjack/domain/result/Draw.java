package blackjack.domain.result;

public class Draw implements Result {

    static final int DEFAULT_BET = 0;

    @Override
    public int calculateBet(final int amount) {
        return DEFAULT_BET;
    }
}
