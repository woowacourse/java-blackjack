package dto;

import domain.Player;

public record PlayerDto(
        String name,
        PlayerResultDto playerResultDto
) {
    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName().getName(), PlayerResultDto.from(player));
    }
}
