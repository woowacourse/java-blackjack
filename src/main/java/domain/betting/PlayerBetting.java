package domain.betting;

import domain.participant.Name;
import domain.participant.Player;

public record PlayerBetting
        (Name playerName,
         BettingAmount bettingAmount
        ) {
    public static PlayerBetting from(Player player, BettingAmount bettingAmount) {
        return new PlayerBetting(player.getName(), bettingAmount);
    }
}
