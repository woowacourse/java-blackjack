package blackjack.dto.delete;

import blackjack.dto.CardDto;
import java.util.List;

public record HandDeckDto(
        List<CardDto> cardDtos,
        int score) {
}
