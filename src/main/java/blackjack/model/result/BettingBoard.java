package blackjack.model.result;

import blackjack.model.player.Player;

import java.util.Map;
import java.util.NoSuchElementException;

public class BettingBoard {
    private static final String PLAYER_NOT_FOUND = "배팅 보드에서 해당 플레이어를 찾을 수 없습니다.";

    private final Map<Player, BettingMoney> bettings; // Todo: Player를 저장할 것인가 Player의 name을 저장할 것인가

    public BettingBoard(final Map<Player, BettingMoney> bettings) {
        this.bettings = Map.copyOf(bettings);
    }

    public BettingMoney determineProfit(final Player player, final MatchResult matchResult) {
        BettingMoney bettingMoney = getBettingMoney(player);
        BettingMoney finalBettingMoney = bettingMoney.fixByMatchResult(matchResult);
        return finalBettingMoney.minus(bettingMoney);
    }

    private BettingMoney getBettingMoney(final Player player) {
        BettingMoney bettingMoney = bettings.get(player);
        if (bettingMoney == null) {
            throw new NoSuchElementException(PLAYER_NOT_FOUND);
        }
        return bettingMoney;
    }
}
