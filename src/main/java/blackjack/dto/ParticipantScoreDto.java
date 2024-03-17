package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import java.util.Map.Entry;

public record ParticipantScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantScoreDto of(final Entry<ParticipantName, Hands> entry, final Score score) {
        ParticipantName name = entry.getKey();
        Hands hands = entry.getValue();

        return new ParticipantScoreDto(ParticipantCardsDto.of(name, hands), score.getValue());
    }
}
