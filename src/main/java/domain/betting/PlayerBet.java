package domain.betting;

import domain.participant.Name;
import domain.participant.Player;

public record PlayerBet(Name playerName, BettingAmount bettingAmount) {
    public static PlayerBet from(Player player, BettingAmount bettingAmount) {
        return new PlayerBet(player.getName(), bettingAmount);
    }
}
