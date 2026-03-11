package dto;

import domain.Player;

public record PlayerHandDto(String name, HandDto handDto) {

    public static PlayerHandDto from(Player player) {
        return new PlayerHandDto(player.getName(), HandDto.from(player.getHand()));
    }
}
