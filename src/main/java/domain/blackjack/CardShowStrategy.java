package domain.blackjack;

import domain.card.Card;
import java.util.List;

public interface CardShowStrategy {
    List<Card> show(List<Card> allCards);
}
