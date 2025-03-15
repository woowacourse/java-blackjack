package domain.game;

import domain.participant.Player;

import java.util.Map;

public class PlayerBets {
    private final Map<Player, BetMoney> playerBets;

    public PlayerBets(Map<Player, BetMoney> playerBets) {
        this.playerBets = playerBets;
    }

    public int getBetAmountByPlayer(Player player) {
        return playerBets.get(player).getAmount();
    }
}
