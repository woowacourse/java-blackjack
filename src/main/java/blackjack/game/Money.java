package blackjack.game;

public class Money {

    private final int money;

    Money(int money) {
        validateNatural(money);
        this.money = money;
    }

    private void validateNatural(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 양수여야 합니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
