package blackjack.view.dto;

import blackjack.model.card.Card;
import blackjack.model.participant.Participant;
import java.util.List;

public record ParticipantCardsOutputRequest(
        String name,
        List<Card> cards
) {
    public static ParticipantCardsOutputRequest from(Participant participant) {
        return new ParticipantCardsOutputRequest(
                participant.getName(),
                participant.getOpenedCards()
        );
    }
}