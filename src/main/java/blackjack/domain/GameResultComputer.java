package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.result.JudgeResult;
import blackjack.dto.TotalGameResult;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResultComputer {

    private final Map<String, JudgeResult> judgeResultsByPlayer;
    private final Map<String, Integer> totalProfitByParticipant = new LinkedHashMap<>();


    public GameResultComputer(final Map<String, JudgeResult> judgeResultsByPlayer) {
        this.judgeResultsByPlayer = judgeResultsByPlayer;
    }

    public Map<JudgeResult, Integer> countDealerJudgeResults() {
        return judgeResultsByPlayer.values()
                .stream()
                .map(JudgeResult::counter)
                .collect(Collectors.toMap(Function.identity(), result -> 1, Integer::sum,
                        () -> new EnumMap<>(JudgeResult.class)));
    }

    public TotalGameResult computeTotalResult() {
        return TotalGameResult.of(countDealerJudgeResults(), judgeResultsByPlayer);
    }

    public Map<String, Integer> calculateProfitByParticipant(final Dealer dealer, final List<Player> players) {
        totalProfitByParticipant.put(dealer.getName(), 0);
        players.forEach(player -> add(dealer, player));
        return totalProfitByParticipant;
    }

    private void add(final Dealer dealer, final Player player) {
        final int playerProfit = dealer.calculateBettingMoney(player);
        totalProfitByParticipant.put(player.getName(), playerProfit);
        totalProfitByParticipant.put(dealer.getName(),
                totalProfitByParticipant.getOrDefault(dealer.getName(), 0) + playerProfit * (-1));
    }
}
