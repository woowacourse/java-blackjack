package domain.dto;

import domain.Score;
import domain.card.Card;
import java.util.List;

public record ParticipantResultResponse(
        String name,
        List<Card> cards,
        Score score
) {
    public ParticipantResultResponse {
        cards = List.copyOf(cards);
    }
}
