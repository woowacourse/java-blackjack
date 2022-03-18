package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public interface State {

    State draw(final Card card);

    State stay();

    PlayingCards playingCards();
}
