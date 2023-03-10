package blackjack.model;

public class BetAmount {

    private final int betAmount;

    public BetAmount(int betAmount) {
        validateIsPositive(betAmount);
        this.betAmount = betAmount;
    }

    private void validateIsPositive(int betAmount){
        if(betAmount <= 0){
            throw new IllegalArgumentException("유효한 금액이 아닙니다. 베팅 금액은 양의 정수여야 합니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
