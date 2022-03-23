package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.Dealer;

public interface State {

    State draw(Card card);

    State stay();

    Cards cards();

    boolean isFinished();

    double profit(Dealer dealer, int money);
}

