package blackjack.domain;

public class BettingMoney {

    public static final String ERROR_VALIDATE_MONEY = "올바른 금액을 입력하여 주세요";

    private int bettingMoney;

    public BettingMoney(String enterMoney) {
        validateMoney(enterMoney);
        this.bettingMoney = Integer.parseInt(enterMoney);
    }

    public BettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }


    private void validateMoney(String bettingMoney) {
        try {
            Integer.parseInt(bettingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MONEY);
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void giveMoney(BettingMoney plusBettingMoney) {
        bettingMoney += plusBettingMoney.getBettingMoney();
    }
}
