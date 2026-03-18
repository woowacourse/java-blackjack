package domain.card;

import java.util.List;

public record CurrentHand(
        String name,
        List<Card> cards
) {
}
