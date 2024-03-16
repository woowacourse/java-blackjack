package gameResult;

import participant.dealer.Dealer;
import participant.player.BetMoney;
import participant.player.Player;

public interface GameResult {

    boolean isCorrespondentResult(Player player, Dealer dealer);

    int profitMoney(BetMoney betMoney);
}
