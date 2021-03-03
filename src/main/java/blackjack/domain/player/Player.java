package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public interface Player {

    void drawCard();

    String getName();

    Score getScore();

    Cards getCards();
}
