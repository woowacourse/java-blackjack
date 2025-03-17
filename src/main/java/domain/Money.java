package domain;

public class Money {

    private final int seedMoney;
    private int earnMoney;

    public Money(int seedMoney) {
        validateMinimumSeedMoney(seedMoney);
        this.seedMoney = seedMoney;
    }

    public static Money makeMoneyInt(int money) {
        return new Money(money);
    }

    private void validateMinimumSeedMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("0보다 큰 금액만 베팅 가능합니다");
        }
    }

    public void processBetting(double rate) {
        earnMoney = (int) Math.round(seedMoney * rate);
    }

    public int getEarnMoney() {
        return earnMoney;
    }

    public void earnMoney(int earnMoney) {
        this.earnMoney += earnMoney;
    }
}
