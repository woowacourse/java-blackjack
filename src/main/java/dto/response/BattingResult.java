package dto.response;

public class BattingResult {

    private final String name;
    private final int money;

    public BattingResult(String name, int money) {
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
