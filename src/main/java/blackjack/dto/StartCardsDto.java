package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.Map;

public record StartCardsDto(List<PlayerCardsDto> playersCards, PlayerCardsDto dealerCards) {
    public static StartCardsDto of(final Map<ParticipantName, Hands> playersCards, final Hands dealerHands, final ParticipantName dealerName) {
        return new StartCardsDto(convertToPlayersCardDto(playersCards), convertToPlayerCardDto(dealerHands, dealerName));
    }

    private static List<PlayerCardsDto> convertToPlayersCardDto(final Map<ParticipantName, Hands> playersCards) {
        return playersCards.entrySet().stream()
                .map(entry -> PlayerCardsDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static PlayerCardsDto convertToPlayerCardDto(final Hands dealerHands, final ParticipantName dealerName) {
        return PlayerCardsDto.of(dealerName, dealerHands);
    }
}
