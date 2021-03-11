package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public interface Player {
    void receiveCard(Card card);

    String getNameValue();

    Score getScore();

    Cards getCards();

    boolean hasBlackJack();

    boolean isBust();

    int getMoneyValue();
}
