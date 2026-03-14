package blackjack.domain.game;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public interface GameReferee {
    GameResult judge(Dealer dealer, Player player);
}
