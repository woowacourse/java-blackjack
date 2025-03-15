package blackjack.domain;

import java.util.Queue;

public interface CardProvider {
    Queue<Card> initializeCards();

    void shuffleCards();

    Card drawCard();
}
