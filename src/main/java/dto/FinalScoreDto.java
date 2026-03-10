package dto;

import java.util.List;

public record FinalScoreDto(
        ParticipantScoreDto dealer,
        List<ParticipantScoreDto> players
) {
}
