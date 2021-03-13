package blackjack.domain.user;

public class PlayerInitialDto {
    private final String name;
    private final int money;

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
