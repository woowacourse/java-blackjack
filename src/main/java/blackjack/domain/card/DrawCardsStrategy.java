package blackjack.domain.card;

import java.util.List;

public interface DrawCardsStrategy {
    List<Card> drawCards(List<Card> cards);
}
