package domain.dto;

import domain.participant.Participants;
import java.util.List;

public record InitialDealingResult(
        ParticipantCards dealerUpCard,
        List<ParticipantCards> allPlayerCardInHand
) {
    public static InitialDealingResult from(Participants participants) {
        ParticipantCards dealer = ParticipantCards.fromDealerUpCard(participants.getDealer());
        List<ParticipantCards> playerCardInHand = participants.players().stream()
                .map(ParticipantCards::fromPlayer)
                .toList();

        return new InitialDealingResult(
                dealer,
                playerCardInHand);
    }
}
