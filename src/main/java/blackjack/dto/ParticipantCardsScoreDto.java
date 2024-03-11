package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.Score;

public record PlayersCardScoreDto(ParticipantCardsDto participantCardsDto, int score) {
    public static PlayersCardScoreDto of(final ParticipantName name, final Hands hands, final Score score) {
        return new PlayersCardScoreDto(ParticipantCardsDto.of(name, hands), score.getValue());
    }
}
