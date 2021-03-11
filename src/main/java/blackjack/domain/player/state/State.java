package blackjack.domain.player.state;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.Result;

public interface State {
    void blackJackCheck(Dealer dealer, Challenger challenger);

    void compareCards(Dealer dealer, Challenger challenger);

    Result getResult();
}

