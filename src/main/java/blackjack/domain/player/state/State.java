package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public interface State {
    boolean drawable();

    State draw(Card card);

    Score score();
}
