package ui.dto;

public record PlayerCreateDto(
        String name,
        String betMoney
) {
    public static domain.dto.PlayerCreateDto toDomain(PlayerCreateDto dto) {
        return new domain.dto.PlayerCreateDto(dto.name, dto.betMoney);
    }
}
