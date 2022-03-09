package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class Rule {
    public int calculate(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
