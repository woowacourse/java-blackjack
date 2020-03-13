package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.List;

public interface Player {
    void giveCards(Card... cards);

    Score getScore();

    boolean isBust();

    List<Card> getHand();

    int countCards();

    String getName();

    Boolean isWinner(Score dealerScore);

    boolean isNotBust();

    boolean isDealer();
}
