package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackResult {
    private static final String WIN_DRAW_LOSE_RESULT_DELIMITER = " ";

    private final Map<String, String> resultMap;

    private BlackjackResult(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public static BlackjackResult match(Player dealer, List<Player> guests) {
        Map<String, String> result = judgeResult(dealer, guests);
        return new BlackjackResult(result);
    }

    private static Map<String, String> judgeResult(Player dealer, List<Player> guests) {
        Map<String, String> resultStrings = new LinkedHashMap<>();
        Map<WinDrawLose, Integer> dealerResult = new EnumMap<>(WinDrawLose.class);
        judgeAndPutResult(dealer, guests, resultStrings, dealerResult);
        resultStrings.put(dealer.getName(), getWinDrawLoseString(dealerResult));
        return resultStrings;
    }

    private static void judgeAndPutResult(Player dealer, List<Player> guests, Map<String, String> resultStrings,
                                   Map<WinDrawLose, Integer> dealerResult) {
        for (Player guest : guests) {
            WinDrawLose result = WinDrawLose.judgeDealerWinDrawLose(dealer, guest);
            dealerResult.merge(result, 1, Integer::sum);
            resultStrings.put(guest.getName(), result.reverse().getName());
        }
    }

    private static String getWinDrawLoseString(Map<WinDrawLose, Integer> winDrawLoseResult) {
        return winDrawLoseResult.entrySet().stream()
                .map(set -> set.getValue() + set.getKey().getName())
                .collect(Collectors.joining(WIN_DRAW_LOSE_RESULT_DELIMITER));
    }

    public Map<String, String> getResultMap() {
        return Collections.unmodifiableMap(resultMap);
    }
}
