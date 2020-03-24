package domain.gamer.dto;

public class GamerMoneyDto {
    private String name;
    private int money;

    public GamerMoneyDto(String name, int money) {
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
