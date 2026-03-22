package ui.dto;

import domain.dto.PlayerCreateInfo;

public record PlayerCreateDto(
        String name,
        String betMoney
) {
    public static PlayerCreateInfo toDomain(PlayerCreateDto dto) {
        return new PlayerCreateInfo(dto.name, dto.betMoney);
    }
}
