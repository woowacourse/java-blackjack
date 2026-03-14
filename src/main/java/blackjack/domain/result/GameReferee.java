package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public interface GameReferee {
    GameResult judge(Dealer dealer, Player player);
}
