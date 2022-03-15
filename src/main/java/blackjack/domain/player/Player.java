package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public interface Player {

    void hit(Card card);

    boolean isBust();

    boolean isBlackjack();

    Name getName();

    PlayerCards getPlayerCards();

    int getScore();

    boolean isDealer();

    boolean canHit();
}
