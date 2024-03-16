package blackjack.dto;

import java.util.List;

public record DealerDto(
        List<CardDto> cardDtos,
        int score) {
}
