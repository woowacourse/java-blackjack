package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import java.util.List;

public record ParticipantCardsDto(String name, List<CardDto> cardDtos) {
    public static ParticipantCardsDto from(final Player player) {
        return new ParticipantCardsDto(player.getName().getName(), convertToCardDto(player.getHands().getCards()));
    }
    public static ParticipantCardsDto of(final ParticipantName name, final List<Card> cards) {
        return new ParticipantCardsDto(name.getName(), convertToCardDto(cards));
    }

    private static List<CardDto> convertToCardDto(final List<Card> values) {
        return values.stream()
                .map(CardDto::from)
                .toList();
    }
}
