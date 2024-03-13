package blackjack.model.result;

import java.util.Map;
import java.util.NoSuchElementException;

public class BettingBoard {
    private static final String PLAYER_NOT_FOUND = "배팅 보드에서 해당 플레이어를 찾을 수 없습니다.";

    private final Map<String, BettingMoney> bettings;

    public BettingBoard(final Map<String, BettingMoney> bettings) {
        this.bettings = Map.copyOf(bettings);
    }

    public BettingMoney determineProfit(final String playerName, final MatchResult matchResult) {
        BettingMoney bettingMoney = getBettingMoney(playerName);
        BettingMoney finalBettingMoney = bettingMoney.fixByMatchResult(matchResult);
        return finalBettingMoney.minus(bettingMoney);
    }

    private BettingMoney getBettingMoney(final String playerName) {
        BettingMoney bettingMoney = bettings.get(playerName);
        if (bettingMoney == null) {
            throw new NoSuchElementException(PLAYER_NOT_FOUND);
        }
        return bettingMoney;
    }
}
