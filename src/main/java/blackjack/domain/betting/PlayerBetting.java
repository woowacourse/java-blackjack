package blackjack.domain.betting;

import blackjack.domain.participant.Player;

public class PlayerBetting {

    private final Player player;
    private final int bettingAmount;

    public PlayerBetting(Player player, int bettingAmount) {
        this.player = player;
        this.bettingAmount = bettingAmount;
    }

    public Player getPlayer() {
        return player;
    }

    public int getBettingAmount() {
        return bettingAmount;
    }

    @Override
    public String toString() {
        return "PlayerBetting{" +
                "player=" + player +
                ", bettingAmount=" + bettingAmount +
                '}';
    }
}
