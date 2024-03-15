package blackjack.domain.betting;

public class Betting {

    private Double bettingMoney;

    public Betting(String bettingMoney) {
        this.bettingMoney = validateMoneyInput(bettingMoney);
    }

    public Betting calculateBettingMoney(Double profitRate) {
        bettingMoney *= profitRate;
        return this;
    }

    public Double getBettingMoney() {
        return bettingMoney;
    }

    private double validateMoneyInput(String bettingMoney) {
        double bettingMoneyValue;

        try {
            bettingMoneyValue = Double.parseDouble(bettingMoney);
            validateMoneyRange(bettingMoneyValue);
            return bettingMoneyValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자여야 합니다.");
        }
    }

    private void validateMoneyRange(double bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 0원보다 많은 돈을 걸어야 합니다.");
        }
    }
}
