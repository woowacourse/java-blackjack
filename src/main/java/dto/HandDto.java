package dto;

import domain.Hand;
import domain.card.Card;
import java.util.List;

public record HandDto(List<String> cards) {

    public static HandDto from(Card card) {
        return new HandDto(List.of(card.getCardName()));
    }

    public static HandDto from(List<Card> cards) {
        List<String> transformedCards = cards.stream()
                .map(Card::getCardName)
                .toList();
        return new HandDto(transformedCards);
    }

    public static HandDto from(Hand hand) {
        List<String> cards = hand.getCardsList().stream()
                .map(Card::getCardName)
                .toList();
        return new HandDto(cards);
    }
}
