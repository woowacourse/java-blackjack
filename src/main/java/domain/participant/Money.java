package domain.participant;

public class Money {
    private final int money;

    public Money(final int money) {
        isDivisibleByTenThousand(money);
        this.money = money;
    }

    private void isDivisibleByTenThousand(int money) {
        if (money % 10000 != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 만원 단위입니다.");
        }
    }
}
