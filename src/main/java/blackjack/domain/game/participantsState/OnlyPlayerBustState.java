package blackjack.domain.game.participantsState;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;

public class OnlyPlayerBustState implements StateExecutor {

    @Override
    public void calculateResult(HashMap<Player, WinTieLose> playersResult, Player player, Dealer dealer) {
        playersResult.put(player, WinTieLose.LOSE);
    }
}
