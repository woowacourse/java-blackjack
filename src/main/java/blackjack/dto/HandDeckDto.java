package blackjack.dto;

import java.util.List;

public record HandDeckDto(
        List<CardDto> cardDtos,
        int score) {
}
