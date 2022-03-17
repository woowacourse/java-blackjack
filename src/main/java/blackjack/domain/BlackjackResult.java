package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackResult {

    private final Map<Player, WinDrawLose> resultMap;

    private BlackjackResult(Map<Player, WinDrawLose> resultMap) {
        this.resultMap = resultMap;
    }

    public static BlackjackResult match(Player dealer, List<Player> guests) {
        Map<Player, WinDrawLose> result = judgeResult(dealer, guests);
        return new BlackjackResult(result);
    }

    private static Map<Player, WinDrawLose> judgeResult(Player dealer, List<Player> guests) {
        Map<Player, WinDrawLose> resultMap = new LinkedHashMap<>();
        judgeAndPutResult(dealer, guests, resultMap);
        return resultMap;
    }

    private static void judgeAndPutResult(Player dealer, List<Player> guests, Map<Player, WinDrawLose> resultMap) {
        for (Player guest : guests) {
            WinDrawLose result = WinDrawLose.judgePlayerWinDrawLose(dealer, guest);
            resultMap.put(guest, result);
        }
    }

    public Map<Player, WinDrawLose> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }

    public Map<Player, Integer> getPrizeResult(Player dealer, BettingBox bettingBox) {
        Map<Player, Integer> prizeResult = new LinkedHashMap<>();
        int playerRevenue = 0;
        for (Player guest : resultMap.keySet()) {
            int prizeMoney = bettingBox.getPrizeMoney(guest, resultMap.get(guest), guest.isBlackjack());
            prizeResult.put(guest, prizeMoney);
            playerRevenue += prizeMoney;
        }
        prizeResult.put(dealer, playerRevenue * -1);
        return prizeResult;
    }
}
