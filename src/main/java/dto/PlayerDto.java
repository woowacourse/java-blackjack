package dto;

import domain.participant.Player;

public record PlayerDto(
        String name,
        ResultDto resultDto
) {
    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getNameValue(), ResultDto.from(player));
    }
}
