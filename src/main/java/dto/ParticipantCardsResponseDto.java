package dto;

import java.util.List;

public record ParticipantCardsResponseDto(
        String name,
        List<String> cards
) {}
