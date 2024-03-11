package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Map;

public record FinalHandsScoreDto(List<PlayerHandsScoreDto> playersHandsScore, PlayerHandsScoreDto dealerHandsScore) {

    public static FinalHandsScoreDto of(final Map<PlayerName, Hands> playersHands, final Dealer dealer) {
        final List<PlayerHandsScoreDto> handsScores = playersHands.entrySet().stream()
                .map(entry -> PlayerHandsScoreDto.of(entry.getKey(), entry.getValue()))
                .toList();

        return new FinalHandsScoreDto(handsScores, PlayerHandsScoreDto.of(new PlayerName(Dealer.DEALER_NAME), dealer.getHands()));
    }
}
