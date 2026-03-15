package blackjack.domain;

public class Money {

    private final long bettingMoney;

    public Money(long bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public static Money fromBettingAmount(long bettingMoney) {
        validateMoney(bettingMoney);
        return new Money(bettingMoney);
    }

    public Money multiplyBettingAmount(double bonusAmount) {
        return new Money((long) (bettingMoney * bonusAmount));
    }

    public Money lossBettingAmount() {
        return new Money(-this.bettingMoney);
    }

    public Money keepBettingAmount() {
        return this;
    }

    public long getBettingMoney() {
        return bettingMoney;
    }

    private static void validateMoney(long bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액을 정확하게 입력해 주세요.");
        }
    }
}
