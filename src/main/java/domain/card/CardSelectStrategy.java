package domain.card;

import java.util.List;

public interface CardSelectStrategy {
    Card select(List<Card> cards);
}
