package dto;

import domain.participant.Player;

public record PlayerStatisticDto(
        String name,
        int profit
) {

    public static PlayerStatisticDto of(Player player, int profit) {
        return new PlayerStatisticDto(player.getName(), profit);
    }
}
