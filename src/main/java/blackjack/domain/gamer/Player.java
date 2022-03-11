package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public interface Player {
    boolean isBust();

    boolean isBlackjack();

    void hit(Card card);

    List<Card> getViewCard();

    void win();

    void draw();

    void lose();

    String getWinDrawLoseString();

    String getName();

    Cards getCards();
}
