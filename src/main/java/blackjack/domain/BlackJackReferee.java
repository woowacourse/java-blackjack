package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackReferee {

    private static final int WIN_MAX_NUMBER = 21;

    private final Map<Player, WinningResult> playerWinningResult = new LinkedHashMap<>();

    public void createResult(final Dealer dealer, final Player player) {
        playerWinningResult.put(player, getPlayerResultByDealerResult(judgeWinOrLose(dealer, player)));
    }

    private WinningResult judgeWinOrLose(final Dealer dealer, final Player player) {
        if (player.calculateCardNumber() > WIN_MAX_NUMBER || dealer.calculateDealerCardNumber() > WIN_MAX_NUMBER) {
            return calculateByBurst(player);
        }
        if (dealer.judgeBlackjack() || player.judgeBlackjack()) {
            return calculateByBlackjack(player, dealer);
        }
        return calculateByNumber(player, dealer);
    }

    private WinningResult calculateByBurst(final Player player) {
        if (player.calculateCardNumber() > WIN_MAX_NUMBER) {
            return WinningResult.WIN;
        }
        return WinningResult.LOSE;
    }

    private WinningResult calculateByBlackjack(final Player player,final Dealer dealer) {
        if (player.judgeBlackjack() && dealer.judgeBlackjack()) {
            return WinningResult.PUSH;
        }
        if (player.judgeBlackjack()) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    private WinningResult calculateByNumber(final Player player,final Dealer dealer) {
        if (player.calculateCardNumber() > dealer.calculateDealerCardNumber()) {
            return WinningResult.LOSE;
        }
        if (player.calculateCardNumber() < dealer.calculateDealerCardNumber()) {
            return WinningResult.WIN;
        }
        return WinningResult.PUSH;
    }

    private WinningResult getPlayerResultByDealerResult(WinningResult dealerWinningResult) {
        if (dealerWinningResult.isLose()) {
            return WinningResult.WIN;
        }
        if (dealerWinningResult.isWin()) {
            return WinningResult.LOSE;
        }
        return WinningResult.PUSH;
    }

    public Map<Player, WinningResult> getPlayerWinningResult() {
        return Collections.unmodifiableMap(playerWinningResult);
    }
}
