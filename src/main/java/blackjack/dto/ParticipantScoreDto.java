package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;
import java.util.Map;
import java.util.Map.Entry;

public record ParticipantScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantScoreDto of(final Entry<ParticipantName, Hands> entry,
                                         final Map<ParticipantName, Score> scoreResult) {
        ParticipantName name = entry.getKey();
        Hands hands = entry.getValue();
        Score score = scoreResult.get(entry.getKey());

        return new ParticipantScoreDto(ParticipantCardsDto.of(name, hands), score.getValue());
    }
}
