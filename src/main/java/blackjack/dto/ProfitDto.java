package blackjack.dto;

public class ProfitDto {

    private final String name;
    private final int money;

    public ProfitDto(String name, int money) {
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
