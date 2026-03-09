package dto;

import domain.card.Card;
import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;

public record CardDto(Rank rank, Suit suit) {
    public static List<CardDto> fromCards(List<Card> cards) {
        return cards.stream()
                .map(c -> new CardDto(c.rank(), c.suit()))
                .toList();
    }
}
