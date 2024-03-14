package blackjack.model.result;

import blackjack.view.dto.PlayerEarning;

import java.util.Map;
import java.util.NoSuchElementException;

public class BettingBoard {
    private static final String PLAYER_NOT_FOUND = "배팅 보드에서 해당 플레이어를 찾을 수 없습니다.";

    private final Map<String, BettingMoney> bettings;

    public BettingBoard(final Map<String, BettingMoney> bettings) {
        this.bettings = Map.copyOf(bettings);
    }

    public PlayerEarning determineEarning(final String playerName, final MatchResult matchResult) {
        BettingMoney bettingMoney = getBettingMoney(playerName);
        int earning = matchResult.calculateEarning(bettingMoney);
        return new PlayerEarning(playerName, earning);
    }

    private BettingMoney getBettingMoney(final String playerName) {
        BettingMoney bettingMoney = bettings.get(playerName);
        if (bettingMoney == null) {
            throw new NoSuchElementException(PLAYER_NOT_FOUND);
        }
        return bettingMoney;
    }
}
