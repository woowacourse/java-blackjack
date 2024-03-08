package blackjack.domain.cardgame;

import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardGameJudge {
    public static final int BUST_THRESHOLD = 21;

    public CardGameResult judge(final Player dealer, final List<Player> players) {
        Map<Player, WinningStatus> result = new LinkedHashMap<>();

        for (final Player player : players) {
            WinningStatus winningStatus = judge(dealer, player);
            result.put(player, winningStatus);
        }

        return new CardGameResult(result);
    }

    private WinningStatus judge(final Player dealer, final Player player) {
        int dealerScore = dealer.getScore();
        int playerScore = player.getScore();

        return doesPlayerWin(dealerScore, playerScore);
    }

    private WinningStatus doesPlayerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_THRESHOLD) {
            return WinningStatus.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            return WinningStatus.WIN;
        }
        if (dealerScore == playerScore) {
            return WinningStatus.PUSH;
        }
        if (dealerScore < playerScore) {
            return WinningStatus.WIN;
        }
        return WinningStatus.LOSE;
    }
}
