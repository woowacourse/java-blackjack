package dto;

import domain.game.Result;
import domain.participant.Player;

public record PlayerStatisticDto(
        String name,
        Result result
) {

    public static PlayerStatisticDto of(Player player, Result result) {
        return new PlayerStatisticDto(player.getName(), result);
    }
}
