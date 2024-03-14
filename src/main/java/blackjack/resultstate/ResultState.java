package blackjack.resultstate;

import blackjack.player.Dealer;
import blackjack.player.Participant;
import blackjack.player.Player;

public interface ResultState {

    boolean isCapableWith(Participant participant, Dealer dealer);

    MatchResult getMatchResult();

    default boolean bothPlayersNotBust(Player player, Player opponent) {
        return player.isNotBust() && opponent.isNotBust();
    }
}
