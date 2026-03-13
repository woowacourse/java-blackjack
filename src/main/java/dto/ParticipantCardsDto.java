package dto;

import domain.Participant;
import java.util.List;

public record ParticipantCardsDto(String name, List<CardInfoDto> cardsInfo, int totalScore) {
    public static ParticipantCardsDto from(Participant participant) {
        List<CardInfoDto> cardInfoDtos = participant.getCards()
                .stream()
                .map(card -> CardInfoDto.from(card.getNumberDisplayName(), card.getShape()))
                .toList();

        return new ParticipantCardsDto(participant.getName(), cardInfoDtos, participant.getScore());
    }
}
