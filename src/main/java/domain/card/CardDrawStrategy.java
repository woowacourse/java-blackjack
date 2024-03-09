package domain.card;

import java.util.List;

public interface CardDrawStrategy {
    Card nextCard(List<Card> cards);
}
