package application.dto;

import domain.Card;
import java.util.List;

public record RoundResult(
        List<Card> cards,
        boolean isBust
) {
}
