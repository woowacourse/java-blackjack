package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public interface Player {

    void draw(Card card);

    boolean canReceive();

    Score getGameScore();

}
