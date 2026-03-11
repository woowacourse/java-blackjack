package dto;

import java.util.List;

public record ParticipantResult(
        String name,
        List<String> cardList,
        int score
) {
}
