package domain.calculatestrategy;

import domain.deck.Card;
import java.util.List;

public interface CalculateStrategy {
    int calculateSum(final List<Card> cards);
}
