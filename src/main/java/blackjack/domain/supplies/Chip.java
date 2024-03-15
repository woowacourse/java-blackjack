package blackjack.domain.supplies;

public class Chip {
    private int money;

    public Chip(int money) {
        this.money = money;
    }

    public void add(int money) {
        this.money += money;
    }

    public int getChip() {
        return money;
    }
}
