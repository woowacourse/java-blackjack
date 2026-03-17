package blackjack.view.dto;

import blackjack.model.card.Card;
import blackjack.model.participant.Participant;
import java.util.List;

public record ParticipantCardsWithScoreOutputRequest(
        String name,
        List<Card> cards,
        int score
) {
    public static ParticipantCardsWithScoreOutputRequest from(Participant participant) {
        return new ParticipantCardsWithScoreOutputRequest(
                participant.getName(),
                participant.getAllCard(),
                participant.getCurrentTotalScore()
        );
    }
}