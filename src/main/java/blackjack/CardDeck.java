package blackjack;

import java.util.List;

public interface CardDeck {
    Card pop();

    boolean isEmpty();

    List<Card> initCards();
}
