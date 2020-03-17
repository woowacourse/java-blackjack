package domain.user;

public class Money {

    private static final int CRITERIA = 0;
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money <= CRITERIA) {
            throw new IllegalArgumentException("배팀금액은 0원 이하일 수 없습니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
