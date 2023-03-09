package domain.game;

public class Bet {
    
    public static final String NEGATIVE_BET_NUMBER_ERROR_MESSAGE = "베팅 금액은 0보다 커야 합니다.";
    private final int bet;
    
    public Bet(int bet) {
        this.validateNegativeBet(bet);
        this.bet = bet;
    }
    
    private void validateNegativeBet(final int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException(NEGATIVE_BET_NUMBER_ERROR_MESSAGE);
        }
    }
    
    public int getBet() {
        return this.bet;
    }
}
