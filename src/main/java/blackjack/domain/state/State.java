package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public interface State {

    void bet(final String betting);

    State draw(final Card card);

    State stay();

    boolean isRunning();

    boolean isFinished();

    void decideRate(final double rate);

    double getEarning();

    PlayingCards getPlayingCards();

    PlayingCards getPartOfPlayingCards();

    int cardTotal();
}
