package dto;

import domain.card.Card;
import java.util.List;

public record RoundResult(
        List<Card> cards,
        boolean isBust
) {
}
