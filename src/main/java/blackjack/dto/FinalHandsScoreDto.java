package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.user.UserName;
import java.util.List;
import java.util.Map;

public record FinalHandsScoreDto(List<PlayerHandsScoreDto> playersHandsScore, Hands dealerHands) {

    public static FinalHandsScoreDto of(final Map<UserName, Hands> playersHands, final Hands dealerHands) {
        final List<PlayerHandsScoreDto> handsScores = playersHands.entrySet().stream()
                .map(entry -> new PlayerHandsScoreDto(entry.getKey(), entry.getValue()))
                .toList();

        return new FinalHandsScoreDto(handsScores, dealerHands);
    }
}
