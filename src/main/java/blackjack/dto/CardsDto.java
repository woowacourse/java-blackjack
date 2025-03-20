package blackjack.dto;

import java.util.List;
import java.util.stream.Collectors;

public record CardsDto(
        List<CardDto> cards
) {

    @Override
    public String toString() {
        return cards().stream()
                .map(CardDto::toString)
                .collect(Collectors.joining(", "));
    }
}
