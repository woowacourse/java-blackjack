package dto;

import java.util.List;

public record BlackjackResultDto(
        ParticipantDto dealerResultDto,
        List<ParticipantDto> playerResultDtoList
) {

    public static BlackjackResultDto of(ParticipantDto dealerResultDto, List<ParticipantDto> playerResultDtoList) {
        return new BlackjackResultDto(dealerResultDto, playerResultDtoList);
    }
}
