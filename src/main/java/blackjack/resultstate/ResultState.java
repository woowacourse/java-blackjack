package blackjack.resultstate;

import blackjack.player.Player;

public interface ResultState {

    boolean isCapableWith(Player participant, Player dealer);

    MatchResult getMatchResult();

    default boolean bothPlayersNotBust(Player player, Player opponent) {
        return player.isNotBust() && opponent.isNotBust();
    }
}
