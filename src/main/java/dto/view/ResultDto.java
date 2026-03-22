package dto.view;

import java.util.List;

public record ResultDto(
        ParticipantResultDto dealer,
        List<ParticipantResultDto> players
) {
}
