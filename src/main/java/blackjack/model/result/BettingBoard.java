package blackjack.model.result;

import blackjack.model.player.Name;
import blackjack.view.dto.PlayerEarning;

import java.util.Map;
import java.util.NoSuchElementException;

public class BettingBoard {
    private static final String PLAYER_NOT_FOUND = "배팅 보드에서 해당 플레이어를 찾을 수 없습니다.";

    private final Map<Name, BettingMoney> bettings;

    public BettingBoard(final Map<Name, BettingMoney> bettings) {
        this.bettings = Map.copyOf(bettings);
    }

    public PlayerEarning determineEarning(final Name playerName, final MatchResult matchResult) {
        BettingMoney bettingMoney = getBettingMoney(playerName);
        int earning = matchResult.calculateEarning(bettingMoney);
        return new PlayerEarning(playerName.getValue(), earning);
    }

    private BettingMoney getBettingMoney(final Name playerName) {
        BettingMoney bettingMoney = bettings.get(playerName);
        if (bettingMoney == null) {
            throw new NoSuchElementException(PLAYER_NOT_FOUND);
        }
        return bettingMoney;
    }
}
