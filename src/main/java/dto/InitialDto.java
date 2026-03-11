package dto;

import java.util.List;

public record InitialDto(
        String joinedNames,
        ParticipantDto dealer,
        List<ParticipantDto> players
) {
}
