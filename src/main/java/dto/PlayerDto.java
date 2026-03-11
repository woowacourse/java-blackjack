package dto;

import java.util.List;

public record PlayerDto(
        String name,
        List<CardDto> cards
) {
}
