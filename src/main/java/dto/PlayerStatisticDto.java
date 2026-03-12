package dto;

import constant.Result;
import domain.participant.Player;

public record PlayerStatisticDto(
        String name,
        Result result
) {

    public static PlayerStatisticDto of(Player player, Result result) {
        return new PlayerStatisticDto(player.getName(), result);
    }
}
