package domain.gamer;

public class PlayerWallet {
    private final Player player;
    private final int money;

    PlayerWallet(Player player, int money) {
        this.player = player;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return player.getName();
    }
}
