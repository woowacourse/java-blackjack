package blackjack.domain.game;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class GameReferee {

    private final Dealer dealer;

    public GameReferee(final Dealer dealer) {
        this.dealer = dealer;
    }

    public ResultType findResultOfPlayer(final Player player) {
        if (isTie(player)) {
            return ResultType.TIE;
        }

        if (isPlayerWin(player)) {
            return ResultType.WIN;
        }

        return ResultType.LOSE;
    }

    private boolean isTie(final Player player) {
        if (player.isBusted() && dealer.isBusted()) {
            return true;
        }

        return player.currentScore() == dealer.currentScore();
    }

    private boolean isPlayerWin(final Player player) {
        if (dealer.isBusted()) {
            return true;
        }
        if (player.isBusted()) {
            return false;
        }
        return player.currentScore()
                .isBiggerThan(dealer.currentScore());
    }

}
