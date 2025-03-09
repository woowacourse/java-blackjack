package dto;

import domain.card.Card;
import java.util.List;

public record ParticipantResultResponse(
    String name,
    List<Card> cards,
    int score
) {

}
