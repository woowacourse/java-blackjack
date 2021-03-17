package blackjack.dto;

public class WinPrizeDto {
    private String name;
    private int money;

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
