package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public interface User {
    String getName();

    void addFirstCards(List<Card> cards);

    List<Card> getCards();

    boolean isGameOver(int gameOverScore);

    int calculateScore(int gameOverScore);

    void addCard(Card makeOneCard);

    List<String> getCardsStatus();
}
