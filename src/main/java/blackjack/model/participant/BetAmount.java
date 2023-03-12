package blackjack.model.participant;

public class BetAmount {

    private static final int MIN_BET_UNIT = 10000;
    private final int betAmount;

    public BetAmount(int betAmount) {
        validateIsPositive(betAmount);
        validateIsMinimumUnitSatisfied(betAmount);
        this.betAmount = betAmount;
    }

    private void validateIsPositive(int betAmount){
        if(betAmount <= 0){
            throw new IllegalArgumentException("유효한 금액이 아닙니다. 베팅 금액은 양의 정수여야 합니다.");
        }
    }
    
    private void validateIsMinimumUnitSatisfied(int betAmount){
        if(isNotSatisfiedBetUnit(betAmount)){
            throw new IllegalArgumentException("베팅은 만원 단위로 가능합니다.");
        }
    }

    private boolean isNotSatisfiedBetUnit(int betAmount) {
        return betAmount % MIN_BET_UNIT != 0;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
