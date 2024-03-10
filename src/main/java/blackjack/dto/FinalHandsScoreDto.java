package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.Map;

public record FinalHandsScoreDto(List<PlayerHandsScoreDto> playersHandsScore, PlayerHandsScoreDto dealerHandsScore) {
    public static FinalHandsScoreDto of(final Map<ParticipantName, Hands> playersHands, final Dealer dealer) {
        final List<PlayerHandsScoreDto> handsScores = playersHands.entrySet().stream()
                .map(entry -> PlayerHandsScoreDto.of(entry.getKey(), entry.getValue()))
                .toList();

        return new FinalHandsScoreDto(handsScores, PlayerHandsScoreDto.of(dealer.getName(), dealer.getHands()));
    }
}
