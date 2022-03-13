package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface Player {

    void hit(Card card);

    boolean isBust();

    boolean isBlackjack();

    boolean isDealer();

    Name getName();

    Cards getCards();

    int getScore();

    boolean isValidRange();
}
