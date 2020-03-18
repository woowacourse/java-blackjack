package blackjack.domain.result;

import blackjack.domain.result.Exception.BettingMoneyException;

public class BettingMoney {
    private int bettingMoney;

    private BettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public static BettingMoney from(String moneyValue) {
        int bettingMoney = parseNumber(moneyValue);
        validateRange(bettingMoney);
        return new BettingMoney(bettingMoney);
    }

    private static int parseNumber(String moneyValue) {
        try {
            return Integer.parseInt(moneyValue);
        } catch (NumberFormatException e) {
            throw new BettingMoneyException("배팅 금액은 숫자만 가능합니다.");
        }
    }

    private static void validateRange(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new BettingMoneyException("배팅 금액은 양수만 가능합니다.");
        }
    }
}
