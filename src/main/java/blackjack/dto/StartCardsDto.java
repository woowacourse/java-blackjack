package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Map;

public record StartCardsDto(List<PlayerCardsDto> playersCards, Hands dealerHands) {

    public static StartCardsDto of(final Map<PlayerName, Hands> playersCards, final Hands dealerHands) {
        return new StartCardsDto(convertToPlayersCardDto(playersCards), dealerHands);
    }

    private static List<PlayerCardsDto> convertToPlayersCardDto(final Map<PlayerName, Hands> playersCards) {
        return playersCards.entrySet().stream()
                .map(entry -> new PlayerCardsDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}
