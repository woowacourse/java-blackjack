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
        int myValue = dealer.calculateDealerCardNumber();
        int playerValue = player.calculateCardNumber();
        if (playerValue > WIN_MAX_NUMBER || myValue > WIN_MAX_NUMBER) {
            return calculateByBurst(playerValue);
        }
        if (dealer.judgeBlackjack() || player.judgeBlackjack()) {
            return calculateByBlackjack(player.judgeBlackjack(), dealer.judgeBlackjack());
        }
        return calculateByNumber(playerValue, myValue);
    }

    private WinningResult calculateByBurst(int playerValue) {
        if (playerValue > WIN_MAX_NUMBER) {
            return WinningResult.WIN;
        }
        return WinningResult.LOSE;
    }

    private WinningResult calculateByBlackjack(boolean isPlayerBlackjack, boolean isDealerBlackjack) {
        if (isPlayerBlackjack && isDealerBlackjack) {
            return WinningResult.PUSH;
        }
        if (isPlayerBlackjack) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    private WinningResult calculateByNumber(int playerValue, int dealerValue) {
        if (playerValue > dealerValue) {
            return WinningResult.LOSE;
        }
        if (playerValue < dealerValue) {
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
