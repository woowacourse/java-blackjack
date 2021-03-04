package blackjack.domain.user;

import blackjack.domain.card.Card;

public interface User {
    void hit(Card card);
    String getCards();
    String getName();
    int getScore();
}
