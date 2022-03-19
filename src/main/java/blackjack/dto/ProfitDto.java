package blackjack.dto;

public class ProfitDto {

    private final String name;
    private final double money;

    public ProfitDto(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }
}
