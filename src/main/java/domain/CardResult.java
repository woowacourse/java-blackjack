package domain;

import domain.card.Card;
import java.util.List;

public record CardResult(
        String name,
        List<Card> cards,
        int score
) {
}
