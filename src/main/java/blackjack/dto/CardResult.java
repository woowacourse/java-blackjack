package blackjack.dto;

import blackjack.domain.participant.Participant;
import java.util.List;

public record CardResult(
        String participantName,
        List<String> cardsName,
        Integer score
) {

    public static CardResult from(Participant participant) {
        return new CardResult(participant.getName(), participant.getCardsName(), participant.getScore());
    }

}
