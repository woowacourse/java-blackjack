package dto;

import domain.enums.Rank;
import domain.enums.Suit;

public record CardDto(Rank rank, Suit suit) {
}
