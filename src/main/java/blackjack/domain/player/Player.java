package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface Player {
    void receiveCard(Card card);

    String getNameValue();

    Cards getCards();

    boolean hasBlackJack();

    int getMoneyValue();

    boolean isBust();

    boolean ableToDraw();
}
