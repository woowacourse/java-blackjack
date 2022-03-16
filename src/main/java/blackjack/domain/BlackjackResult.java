package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackResult {

    private final Map<String, Map<WinDrawLose, Integer>> resultMap;

    private BlackjackResult(Map<String, Map<WinDrawLose, Integer>> resultMap) {
        this.resultMap = resultMap;
    }

    public static BlackjackResult match(Player dealer, List<Player> guests) {
        Map<String, Map<WinDrawLose, Integer>> result = judgeResult(dealer, guests);
        return new BlackjackResult(result);
    }

    private static Map<String, Map<WinDrawLose, Integer>> judgeResult(Player dealer, List<Player> guests) {
        Map<String, Map<WinDrawLose, Integer>> resultMap = new LinkedHashMap<>();
        Map<WinDrawLose, Integer> dealerResult = new EnumMap<>(WinDrawLose.class);
        judgeAndPutResult(dealer, guests, resultMap, dealerResult);
        resultMap.put(dealer.getName(), dealerResult);
        return resultMap;
    }

    private static void judgeAndPutResult(
            Player dealer, List<Player> guests,
            Map<String, Map<WinDrawLose, Integer>> resultMap,
            Map<WinDrawLose, Integer> dealerResult
    ) {
        for (Player guest : guests) {
            WinDrawLose result = WinDrawLose.judgeDealerWinDrawLose(dealer, guest);
            dealerResult.merge(result, 1, Integer::sum);
            EnumMap<WinDrawLose, Integer> guestResult = new EnumMap<>(WinDrawLose.class);
            guestResult.put(result.reverse(), 1);
            resultMap.put(guest.getName(), guestResult);
        }
    }

    public Map<String, Map<WinDrawLose, Integer>> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }
}
