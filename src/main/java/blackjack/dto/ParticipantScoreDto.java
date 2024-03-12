package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;

public record ParticipantCardsScoreDto(ParticipantCardsDto participantCards, int score) {
    public static ParticipantCardsScoreDto of(final ParticipantName name, final Hands hands, final Score score) {
        return new ParticipantCardsScoreDto(ParticipantCardsDto.of(name, hands), score.getValue());
    }
}
