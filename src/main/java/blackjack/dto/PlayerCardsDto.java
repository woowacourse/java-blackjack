package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;

public record PlayerCardsDto(String name, List<CardDto> cards) {
    public static PlayerCardsDto of(final String name, final Hands hands) {
        return new PlayerCardsDto(name, convertToCardDto(hands));
    }

    public static PlayerCardsDto of(final ParticipantName name, final List<CardDto> cards) {
        return new PlayerCardsDto(name.getName(), cards);
    }

    private static List<CardDto> convertToCardDto(final Hands hands) {
        return hands.getCards().stream()
                .map(CardDto::from)
                .toList();
    }
}
