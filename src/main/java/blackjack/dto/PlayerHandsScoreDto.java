package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;

public record PlayerHandsScoreDto(
        String name,
        HandsScoreDto handsScore
) {
    public static PlayerHandsScoreDto of(final ParticipantName name, final Hands hands) {
        return new PlayerHandsScoreDto(name.getName(), HandsScoreDto.from(hands));
    }
}
