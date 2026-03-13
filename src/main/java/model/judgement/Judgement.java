package model.judgement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.judgement.strategy.BlackjackStrategy;
import model.judgement.strategy.BustStrategy;
import model.judgement.strategy.ScoreComparisonStrategy;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;

public class Judgement {

    private static final List<JudgeStrategy> STRATEGIES = List.of(
            new BustStrategy(),
            new BlackjackStrategy(),
            new ScoreComparisonStrategy()
    );

    public static PlayerResult judgeByPlayer(Dealer dealer, Players players) {
        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, decide(dealer, player)));
        return new PlayerResult(result);
    }

    private static ResultStatus decide(Dealer dealer, Player player) {
        return STRATEGIES.stream()
                .filter(strategy -> strategy.isApplicable(dealer, player))
                .findFirst()
                .map(strategy -> strategy.getResult(dealer, player))
                .orElse(ResultStatus.LOSE);
    }
}
