package domain.money;

import domain.game.ResultStatus;
import java.util.Objects;

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
    
    public Profit calculateProfitFromResult(final ResultStatus resultStatus) {
        return new Profit((int) (this.bet * resultStatus.getWeight()));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.bet);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Bet bet1 = (Bet) o;
        return this.bet == bet1.bet;
    }
}
