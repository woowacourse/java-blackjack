package blackjack.dto;

public class WinPrizeDto {
    String name;
    int money;

    public WinPrizeDto(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
