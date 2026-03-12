package dto;

import domain.card.Card;
import java.util.List;

public record CardDto(String rank, String suit) {
    public static List<CardDto> fromCards(List<Card> cards) {
        return cards.stream()
                .map(c -> new CardDto(c.rank().getRank(), c.suit().getSuit()))
                .toList();
    }
}
