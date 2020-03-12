package blackjack.user;

import blackjack.card.Card;
import blackjack.card.Score;

import java.util.List;

public interface User {
    void append(Card card);

    Score calculateScore();

    boolean isBust();

    List<Card> getCards();

    String getName();

    Boolean isWinner(Score dealerScore);
}
