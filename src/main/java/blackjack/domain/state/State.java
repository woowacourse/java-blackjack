package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public interface State {

    State draw(final Card card);

    State stay();

    boolean isRunning();

    boolean isFinished();

    void decideRate(final double rate);

    double getEarningRate();

    PlayingCards getPlayingCards();

    PlayingCards getPartOfPlayingCards();

    int cardTotal();
}
