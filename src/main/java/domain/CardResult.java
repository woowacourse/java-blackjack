package domain;

import domain.card.Card;
import java.util.List;

// TODO: 이걸 활용할지 고민 중
public record CardResult(
        String name,
        List<Card> cards,
        int score
) {
}
