package domain;

import domain.dto.GameResultResponse;
import domain.dto.PlayerMatchResult;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Referee {

    public GameResultResponse evaluateMatch(Participants participants) {
        List<PlayerMatchResult> playerResults = calculatePlayerResults(participants);
        EnumMap<MatchResult, Integer> dealerResults = calculateDealerResults(playerResults);

        return new GameResultResponse(dealerResults, playerResults);
    }

    private List<PlayerMatchResult> calculatePlayerResults(Participants participants) {
        List<PlayerMatchResult> results = new ArrayList<>();
        Dealer dealer = participants.getDealer();

        for (Player player : participants.getPlayers()) {
            results.add(new PlayerMatchResult(player.getName(), judge(dealer, player)));
        }
        return results;
    }

    private EnumMap<MatchResult, Integer> calculateDealerResults(List<PlayerMatchResult> playerResults) {
        EnumMap<MatchResult, Integer> counts = new EnumMap<>(MatchResult.class);
        for (PlayerMatchResult playerResult : playerResults) {
            counts.merge(playerResult.result().opposite(), 1, Integer::sum);
        }
        return counts;
    }

    private MatchResult judge(Dealer dealer, Player player) {
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return determineByScore(dealer.getScore(), player.getScore());
    }

    private MatchResult determineByScore(Score dealerScore, Score playerScore) {
        int compareResult = playerScore.compareTo(dealerScore);

        if (compareResult > 0) {
            return MatchResult.WIN;
        }
        if (compareResult < 0) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }
}
