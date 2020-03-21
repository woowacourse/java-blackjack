package domain.gamer.dto;

public class GamerMoneyDto extends GamerBaseDto {
    private int money;

    public GamerMoneyDto(String name, int money) {
        super(name);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
