package blackjack.dto;

import blackjack.model.card.Rank;
import blackjack.model.card.Suit;

public record CardDto(Rank rank, Suit suit) {
}
