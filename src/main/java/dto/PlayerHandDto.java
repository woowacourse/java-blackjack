package dto;

import domain.participant.Player;

public record PlayerHandDto(String name, HandDto handDto) {

    public static PlayerHandDto from(Player player) {
        return new PlayerHandDto(player.getNameString(), HandDto.from(player.getHand()));
    }
}
