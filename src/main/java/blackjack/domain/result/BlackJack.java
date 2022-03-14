package blackjack.domain.result;

public class BlackJack implements Result{

    private static final double CALCULATE_UNIT = 1.5;

    @Override
    public int calculateBet(int amount) {
        return (int) (amount * CALCULATE_UNIT);
    }
}
