package domain;

public class Money {
    private final int bettingMoney;

    public Money(int bettingMoney) {
        validatePositiveBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validatePositiveBettingMoney(int bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다. 현재 배팅액 : " + bettingMoney);
        }
    }
}
