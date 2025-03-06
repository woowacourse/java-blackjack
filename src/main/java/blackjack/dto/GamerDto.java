package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Gamer;

public record GamerDto(
    String name,
    List<CardDto> cards
) {

    public static GamerDto from(Gamer gamer) {
        return new GamerDto(
            gamer.getName(),
            gamer.getCards().stream()
                .map(CardDto::from)
                .toList());
    }
}
