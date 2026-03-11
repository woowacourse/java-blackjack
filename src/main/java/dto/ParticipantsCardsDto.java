package dto;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
import java.util.List;

public record ParticipantsCardsDto(String name, List<String> cards) {
    public static ParticipantsCardsDto playerFromEntity(Player player) {
        return new ParticipantsCardsDto(player.getName(), cardToString(player.getCards()));
    }

    public static ParticipantsCardsDto dealerFromEntity(Dealer dealer) {
        List<Card> showOnlyFirst = List.of(dealer.getCards().getFirst());
        return new ParticipantsCardsDto(dealer.getName(), cardToString(showOnlyFirst));
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }
}
