package dto;

import domain.card.Card;
import java.util.List;

public record ParticipantsCardsDto(String name, List<String> cards) {

    public static ParticipantsCardsDto ofDealerOpen(String dealerName, List<Card> cards) {
        return new ParticipantsCardsDto(dealerName, cardToString(cards));
    }

    public static ParticipantsCardsDto ofDealerAll(String dealerName, List<Card> cards) {
        return new ParticipantsCardsDto(dealerName, cardToString(cards));
    }

    public static ParticipantsCardsDto ofPlayer(String playerName, List<Card> cards) {
        return new ParticipantsCardsDto(playerName, cardToString(cards));
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();
    }
}
