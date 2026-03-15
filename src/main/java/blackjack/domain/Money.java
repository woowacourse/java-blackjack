package blackjack.domain;

public class Money {

    private final long bettingMoney;

    public Money(long bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public long getBettingMoney() {
        return bettingMoney;
    }

    private void validateMoney(long bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액을 정확하게 입력해 주세요.");
        }
    }
}
