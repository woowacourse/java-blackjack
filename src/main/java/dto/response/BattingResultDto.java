package dto.response;

public class BattingResultDto {

    private final String name;
    private final int money;

    public BattingResultDto(String name, int money) {
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
