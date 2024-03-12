package blackjack.domain.cardgame;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardGameJudge {
    public CardGameResult judge(final Dealer dealer, final List<Player> players) {
        final Map<Player, WinningStatus> result = new LinkedHashMap<>();

        for (final Player player : players) {
            WinningStatus winningStatus = findPlayerWinningStatus(dealer, player);
            result.put(player, winningStatus);
        }

        return new CardGameResult(result);
    }

    private WinningStatus findPlayerWinningStatus(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return WinningStatus.LOSE;
        }
        if (dealer.isBust()) {
            return WinningStatus.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return WinningStatus.PUSH;
        }
        if (dealer.getScore() < player.getScore()) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
