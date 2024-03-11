package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Map;

public record StartCardsDto(List<PlayerCardsDto> playersCards, PlayerCardsDto dealerCards, int eachCardCount) {

    public static StartCardsDto of(final Map<PlayerName, Hands> playersCards, final Hands dealerHands,
            final PlayerName dealerName) {
        return new StartCardsDto(convertToPlayersCardDto(playersCards),
                convertToPlayerCardDto(dealerHands, dealerName), dealerHands.size());
    }

    private static List<PlayerCardsDto> convertToPlayersCardDto(final Map<PlayerName, Hands> playersCards) {
        return playersCards.entrySet().stream()
                .map(entry -> PlayerCardsDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static PlayerCardsDto convertToPlayerCardDto(final Hands dealerHands, final PlayerName dealerName) {
        return PlayerCardsDto.of(dealerName, dealerHands);
    }
}
