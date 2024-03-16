package domain.gamer.bet;

import domain.gamer.Player;

public class GamerWallet {

    private final Player player;
    private Money money;

    public GamerWallet(Player player, Money money) {
        this.player = player;
        this.money = money;
    }

    public String playerName() {
        return player.getPlayerName();
    }

    public int getMoney() {
        return money.getMoney();
    }

    public Player getPlayer() {
        return player;
    }

    public void applyBetProfit(Money money) {
        this.money = money;
    }
}
