package blackjack.model.betting;

import blackjack.model.player.MatchResult;
import blackjack.model.player.PlayerName;
import java.util.HashMap;
import java.util.Map;

public class Betting {
    private static final String NOT_FOUND_BETTING_PLAYER = "베팅 정보가 없는 플레이어입니다.";

    private final Map<PlayerName, BettingMoney> playerBettingMoney;

    public Betting(final Map<PlayerName, BettingMoney> playerBettingMoney) {
        this.playerBettingMoney = new HashMap<>(playerBettingMoney);
    }

    public int calculatePlayerBettingProfit(final PlayerName playerName, final MatchResult matchResult) {
        BettingMoney bettingMoney = findBettingMoneyByPlayerName(playerName);
        return matchResult.calculateProfit(bettingMoney);
    }

    private BettingMoney findBettingMoneyByPlayerName(final PlayerName playerName) {
        if (!playerBettingMoney.containsKey(playerName)) {
            throw new IllegalArgumentException(NOT_FOUND_BETTING_PLAYER);
        }
        return playerBettingMoney.get(playerName);
    }
}
