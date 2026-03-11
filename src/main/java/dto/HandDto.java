package dto;

import domain.Hand;
import domain.card.Card;
import java.util.List;

public record HandDto(List<String> cards) {

    public static HandDto from(Hand hand) {
        List<String> cards = hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
        return new HandDto(cards);
    }
}
