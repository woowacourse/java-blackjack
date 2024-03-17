package blackjack.dto;

import java.util.List;

public record PlayerDto(
        String name,
        List<CardDto> cardDtos,
        int score) {
}
