package dto;

import domain.card.Card;
import java.util.List;

public record FinalResultDTO(String name, List<Card> cards, int score) {
}
