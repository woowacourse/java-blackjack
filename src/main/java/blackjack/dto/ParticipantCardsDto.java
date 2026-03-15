package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import java.util.List;

public record ParticipantCardsDto(
    String participantName,
    List<CardNameDto> cards
) {
    public static ParticipantCardsDto from(Player player) {
        return new ParticipantCardsDto(
            player.getName(),
            convertCards(player.getCards()));
    }

    public static ParticipantCardsDto of(Dealer dealer, long size) {
        return new ParticipantCardsDto(
            dealer.getName(),
            convertCards(dealer.getCards()));
    }

    private static List<CardNameDto> convertCards(List<Card> cards) {
        return cards.stream()
            .map(CardNameDto::from)
            .toList();
    }
}
