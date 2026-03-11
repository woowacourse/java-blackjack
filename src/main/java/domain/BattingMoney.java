package domain;

public record BattingMoney(int money){
    private static final int MINIMUM_MONEY = 1000;
    private static final int MAXIMUM_MONEY = 1000000;

    public BattingMoney {
        validateAmountRange(money);
    }

    public void validateAmountRange(int money) {
        if (money < MINIMUM_MONEY || money > MAXIMUM_MONEY) {
            throw new IllegalArgumentException("[Error] 배팅 금액은 1,000 ~ 1,000,000원 사이입니다.");
        }
    }
}
