package blackjack.domain.result;

public class BlackJack {

    private static final double CALCULATE_UNIT = 1.5;

    public int calculateBet(int amount) {
        return (int) (amount * CALCULATE_UNIT);
    }
}
