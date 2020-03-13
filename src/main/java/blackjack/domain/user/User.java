package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.result.WinOrLose;

import java.util.List;

public interface User {
    void drawCards(Card... cards);

    Score calculateScore();

    boolean isBust();

    List<Card> getCards();

    boolean is(String name);

    int countCards();

    String getName();

    WinOrLose isWinner(Dealer dealer);

    boolean isNotBust();
}
