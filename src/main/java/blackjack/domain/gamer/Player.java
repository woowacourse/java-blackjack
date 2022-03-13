package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface Player {
    boolean isBust();

    boolean isBlackjack();

    void hit(Card card);

    Cards getShowCards();

    void win();

    void draw();

    void lose();

    String getWinDrawLoseString();

    String getName();

    Cards getCards();
}
