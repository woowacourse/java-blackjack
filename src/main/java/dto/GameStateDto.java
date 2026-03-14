package dto;

import java.util.List;

public record GameStateDto(
        ParticipantDto dealerDto,
        List<ParticipantDto> multiPlayersDtos
) {

    public static GameStateDto from(ParticipantDto dealerDto, List<ParticipantDto> multiPlayersDtos) {
        return new GameStateDto(dealerDto, multiPlayersDtos);
    }
}
