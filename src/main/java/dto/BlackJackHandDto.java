package dto;

import domain.Hand;
import domain.Player;
import domain.card.Card;

import java.util.stream.Collectors;

public record BlackJackHandDto(String handOutput) {

    public static BlackJackHandDto from(Player player) {
        return new BlackJackHandDto(player.getName() + "카드: " + getHandString(player.getHand()));
    }

    private static String getHandString(Hand hand) {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }
}
