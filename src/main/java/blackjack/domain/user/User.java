package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

import java.util.List;

public interface User {

    Score calculateScore();

    boolean isBust();

    boolean is(String name);

    int countCards();

    boolean isNotBust();

    void drawCard(Deck deck);

    void receiveInitialCards(Deck deck);

    String getName();

    List<Card> getCards();
}
