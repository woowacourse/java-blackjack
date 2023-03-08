package dto.response;

public class BattingResultDto {

    private final String name;
    private final double money;

    public BattingResultDto(String name, double money) {
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
