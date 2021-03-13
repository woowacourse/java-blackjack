package blackjack.domain.user;

public class PlayerInitialDto {
    private String name;
    private int money;

    public PlayerInitialDto(String name, int money) {
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
