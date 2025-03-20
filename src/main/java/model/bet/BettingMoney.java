package model.bet;

import exception.IllegalBlackjackInputException;

public record BettingMoney(int money) {

    public BettingMoney {
        validateBettingMoneyRange();
        validateBettingMoneyUnit();
    }

    private void validateBettingMoneyRange() {
        if (money < 1000 || money > 1_000_000) {
            throw new IllegalBlackjackInputException("베팅 금액은 1000원 이상, 100만원 이하이어야합니다.");
        }
    }

    private void validateBettingMoneyUnit() {
        if (money % 1000 != 0) {
            throw new IllegalBlackjackInputException("베팅 금액은 1000원 단위이어야합니다.");
        }
    }
}
