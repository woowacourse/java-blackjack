package blackjack.domain.game.participantsState;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;

public class PlayerDealerState {
    private final Player player;
    private final Dealer dealer;
    private StateExecutor stateExecutor;

    public PlayerDealerState(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
        setBothBustCase();
        setOnlyPlayerBustCase();
        setOnlyDealerBustCase();
        setBothNotBustCase();
    }

    public void calculateResult(HashMap<Player, WinTieLose> playersResult) {
        this.stateExecutor.calculateResult(playersResult, player, dealer);
    }

    private void setBothNotBustCase() {
        if (dealer.isNotBust() && player.isNotBust()) {
            this.stateExecutor = new BothNotBustState();
        }
    }

    private void setOnlyDealerBustCase() {
        if (dealer.isBust() && player.isNotBust()) {
            this.stateExecutor = new OnlyDealerBustState();
        }
    }

    private void setOnlyPlayerBustCase() {
        if (dealer.isNotBust() && player.isBust()) {
            this.stateExecutor = new OnlyPlayerBustState();
        }
    }

    private void setBothBustCase() {
        if (dealer.isBust() && player.isBust()) {
            this.stateExecutor = new BothBustState();
        }
    }
}
