package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGameResult {

    public static final int CHANGE_DEALER_PROFIT = -1;
    private final Map<Player, WinningResult> gameResult;

    public BlackjackGameResult(Map<Player, WinningResult> gameResult) {
        this.gameResult = new LinkedHashMap<>(gameResult);
    }

    public Map<Player, BigDecimal> calculatePlayersPrizeByGameResult() {
        Map<Player, BigDecimal> playersPrize = new LinkedHashMap<>();
        for (Player player : gameResult.keySet()) {
            playersPrize.put(
                    player,
                    player.calculateAmountByGameResult(gameResult.get(player)).setScale(0, RoundingMode.FLOOR)
            );
        }
        return playersPrize;
    }

    public BigDecimal calculateDealerPrizeByGameResult() {
        BigDecimal dealerPrize = BigDecimal.ZERO;
        for (Player player : gameResult.keySet()) {
            dealerPrize = dealerPrize.add(
                    new BigDecimal(CHANGE_DEALER_PROFIT).
                            multiply(player.calculateAmountByGameResult(gameResult.get(player)).
                                    setScale(0, RoundingMode.FLOOR)
                            )
            );
        }
        return dealerPrize;
    }

    public Map<Player, WinningResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public WinningResult NameByPlayer(Player player) {
        return gameResult.get(player);
    }
}
