package domain.dto;

import domain.participant.Participant;

import java.util.List;

public record FinalPlayerCardDto(String name, List<CardDto> cards, int total) {
    public static FinalPlayerCardDto from(Participant participant) {
        List<CardDto> cardDtos = participant.getCards().stream()
                .map(CardDto::from)
                .toList();

        return new FinalPlayerCardDto(
                participant.getName(),
                cardDtos,
                participant.getFinalScore()
        );
    }
}
