package domain.dto;

import domain.card.Card;
import java.util.List;

public record ParticipantCardsResponse(
        String name,
        List<Card> cards
) {
    public ParticipantCardsResponse {
        cards = List.copyOf(cards);
    }
}
