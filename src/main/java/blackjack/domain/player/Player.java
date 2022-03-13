package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public interface Player {

    void hit(Card card);

    boolean isBust();

    boolean isBlackjack();

    boolean isDealer();

    Name getName();

    PlayerCards getPlayerCards();

    int getScore();

    boolean isValidRange();
}
