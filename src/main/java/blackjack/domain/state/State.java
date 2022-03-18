package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public interface State {

    void bet(final String betting);

    State draw(final Card card);

    State stay();

    boolean isFinished();

    double profit();

    PlayingCards playingCards();

    PlayingCards partOfPlayingCards();

    int cardTotal();
}
