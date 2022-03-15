package blackjack.domain.result;

public class BlackJack implements ResultStrategy {

    private static final double CALCULATE_UNIT = 1.5;

    @Override
    public int calculateBet(final int amount) {
        return (int) (amount * CALCULATE_UNIT);
    }
}
