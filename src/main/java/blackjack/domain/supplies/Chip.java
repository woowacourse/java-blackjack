package blackjack.domain.supplies;

public class Chip {
    private int money;

    public Chip(int money) {
        validateNaturalNumber(money);
        this.money = money;
    }

    private void validateNaturalNumber(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("0 이상의 정수를 입력해 주세요.");
        }
    }

    public void add(int money) {
        this.money += money;
    }

    public int getChip() {
        return money;
    }
}
