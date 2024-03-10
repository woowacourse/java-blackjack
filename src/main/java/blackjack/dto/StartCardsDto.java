package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.Map;

public record StartCardsDto(List<PlayerCardsDto> playersCards, List<CardDto> dealerCards) {
    public static StartCardsDto of(final Map<ParticipantName, Hands> playersCards, final Hands dealerHands) {
        return new StartCardsDto(convertToPlayersCardDto(playersCards), convertToCardDto(dealerHands));
    }

    private static List<PlayerCardsDto> convertToPlayersCardDto(final Map<ParticipantName, Hands> playersCards) {
        return playersCards.entrySet().stream()
                .map(entry -> PlayerCardsDto.of(entry.getKey(), convertToCardDto(entry.getValue())))
                .toList();
    }

    private static List<CardDto> convertToCardDto(final Hands hands) {
        return hands.getCards().stream()
                .map(CardDto::from)
                .toList();
    }
}
