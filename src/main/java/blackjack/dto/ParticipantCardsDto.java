package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.participant.ParticipantName;
import java.util.List;

public record StartCardsDto(String participantName, List<CardDto> cardDtos) {
    public static StartCardsDto of(final ParticipantName name, final Hands cards) {
        return new StartCardsDto(name.getName(), convertToCardDto(cards.getCards()));
    }

    private static List<CardDto> convertToCardDto(final List<Card> values) {
        return values.stream()
                .map(CardDto::from)
                .toList();
    }
}
