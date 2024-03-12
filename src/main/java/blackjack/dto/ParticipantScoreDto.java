package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;

public record ParticipantScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantScoreDto of(final ParticipantName name, final Hands hands, final Score score) {
        return new ParticipantScoreDto(ParticipantCardsDto.of(name, hands), score.getValue());
    }
}
