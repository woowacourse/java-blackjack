package domain;

import domain.gamer.Player;

public class PlayerBet {

    private final Player player;
    private final BetAmount betAmount;

    public PlayerBet(Player player, BetAmount betAmount) {
        this.player = player;
        this.betAmount = betAmount;
    }
}
