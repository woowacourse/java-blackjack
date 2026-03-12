package dto.response;

import java.util.List;

public record ParticipantHandResponseDto(
        String name,
        List<String> cards
) {}
