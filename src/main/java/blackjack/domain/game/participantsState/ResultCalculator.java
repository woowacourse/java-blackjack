package blackjack.domain.game.participantsState;

import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.HashMap;
@FunctionalInterface
public interface ResultCalculator {
    void calculateResult(HashMap<Player, WinTieLose> playersResult, Player player, Dealer dealer);
}
