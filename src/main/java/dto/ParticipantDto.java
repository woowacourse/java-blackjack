package dto;

import java.util.List;

public record ParticipantDto(
        String name,
        List<String> hand,
        int score
) {

}
