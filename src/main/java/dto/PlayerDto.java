package dto;

import domain.Player;

public record PlayerDto(
        String name,
        ResultDto resultDto
) {
    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getNameValue(), ResultDto.from(player));
    }
}
