package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;

public record ParticipantCardsDto(String name, List<CardDto> cardDtos) {
    public static ParticipantCardsDto of(final ParticipantName name, final Hands cards) {
        return new ParticipantCardsDto(name.getName(), convertToCardDto(cards.getCards()));
    }

    private static List<CardDto> convertToCardDto(final List<Card> values) {
        return values.stream()
                .map(CardDto::from)
                .toList();
    }

    public static ParticipantCardsDto of(final ParticipantName name, final Card card) {
        return new ParticipantCardsDto(name.getName(), List.of(CardDto.from(card)));
    }
}
