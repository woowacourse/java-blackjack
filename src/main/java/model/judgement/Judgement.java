package model.judgement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.judgement.strategy.BlackjackStrategy;
import model.judgement.strategy.BustStrategy;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Player;
import model.paticipant.Players;

public class Judgement {

    private static final List<JudgeStrategy> STRATEGIES = List.of(new BustStrategy(), new BlackjackStrategy());

    public static PlayerResult judgeByPlayer(Dealer dealer, Players players) {
        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, decide(dealer, player)));
        return new PlayerResult(result);
    }

    private static ResultStatus decide(Dealer dealer, Participant participant) {
        return STRATEGIES.stream()
                .filter(strategy -> strategy.isApplicable(dealer, participant))
                .findFirst()
                .map(strategy -> strategy.getResult(dealer, participant))
                .orElseGet(() -> compareScore(participant.calculateTotalScore(), dealer.calculateTotalScore()));
    }

    private static ResultStatus compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return ResultStatus.WIN;
        }
        if (playerScore == dealerScore) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }
}
