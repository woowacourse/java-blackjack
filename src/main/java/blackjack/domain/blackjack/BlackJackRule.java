package blackjack.domain.blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public interface BlackJackRule {

    ResultType calculateDealerResult(Dealer dealer, Player player);
}
