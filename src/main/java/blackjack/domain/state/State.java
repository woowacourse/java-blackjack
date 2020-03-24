package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;

public interface State {
    State draw(PlayingCard card);

    State stay();

    boolean isFinished();

    Cards cards();

    double profit(double money);
}
