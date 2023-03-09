package blackjack.domain.game.resultCalculator;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;

public class ResultCalculatorWithBustPlayer implements ResultCalculator {
    @Override
    public void calculateResult(HashMap<Player, WinTieLose> playersResult, Player player, Dealer dealer) {
        if (dealer.isBust()) {
            playersResult.put(player, WinTieLose.TIE);
            return;
        }
        playersResult.put(player, WinTieLose.LOSE);
    }
}
