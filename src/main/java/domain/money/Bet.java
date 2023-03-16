package domain.money;

import java.util.Objects;

public class Bet {
    
    public static final String NEGATIVE_BET_NUMBER_ERROR_MESSAGE = "베팅 금액은 0보다 커야 합니다.";
    public static final String BET_UNIT_ERROR_MESSAGE = "베팅 금액은 100원 단위로 입력해주세요.";
    public static final int BET_UNIT = 100;
    private final int bet;
    
    public Bet(int bet) {
        this.validateNegativeBet(bet);
        this.validateBetUnit(bet);
        this.bet = bet;
    }
    
    private void validateNegativeBet(final int bet) {
        if (bet < 0) {
            throw new IllegalArgumentException(NEGATIVE_BET_NUMBER_ERROR_MESSAGE);
        }
    }
    
    private void validateBetUnit(final int bet) {
        if (bet % BET_UNIT != 0) {
            throw new IllegalArgumentException(BET_UNIT_ERROR_MESSAGE);
        }
    }
    
    public Bet add(final Bet bet) {
        return new Bet(this.bet + bet.bet);
    }
    
    public int getBet() {
        return this.bet;
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
