package dto;

import domain.Card;
import domain.Dealer;
import domain.Player;

import java.util.List;

public record PlayerCardsDto(String name, List<String> cards) {
    public static PlayerCardsDto fromEntity(Player player){
        return new PlayerCardsDto(player.getName(), cardToString(player.getCards()));
    }

    public static PlayerCardsDto dealerFromEntity(Dealer dealer){
        List<Card> showOnlyFirst = List.of(dealer.getCards().getFirst());
        return new PlayerCardsDto(dealer.getName(),cardToString(showOnlyFirst));
    }

    private static List<String> cardToString(List<Card> hands) {
        return hands.stream()
                .map(card -> card.rank().getDisplayName() + card.suit().getDisplayName())
                .toList();

    }
}
