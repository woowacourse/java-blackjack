package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.List;

public interface Player {
    void giveCards(Card... cards);

    Score calculateScore();

    boolean isBust();

    List<Card> getCards();

    boolean isName(String name);

    int countCards();

    String getName();

    Boolean isWinner(Score dealerScore);

    boolean isNotBust();
}
