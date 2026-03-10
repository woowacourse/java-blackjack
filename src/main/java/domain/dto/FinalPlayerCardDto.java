package domain.dto;

import domain.participant.Player;

import java.util.List;

public record FinalPlayerCardDto(String name, List<CardDto> cards, int total) {
    public static FinalPlayerCardDto from(Player player) {
        List<CardDto> cardDtos = player.getCards().stream()
                .map(CardDto::from)
                .toList();

        return new FinalPlayerCardDto(
                player.getName(),
                cardDtos,
                player.getFinalScore()
        );
    }
}
