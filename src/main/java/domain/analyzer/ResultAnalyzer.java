package domain.analyzer;

import domain.analyzer.dto.PlayerGameResult;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;

public class ResultAnalyzer {

    private ResultAnalyzer() {

    }

    public static ResultAnalysisDto analyze(List<Player> players, Dealer dealer) {
        List<PlayerGameResult> playerGameResults = players.stream()
                .map(player -> PlayerGameResult.of(player, GameResult.judge(dealer, player)))
                .toList();
        return ResultAnalysisDto.from(playerGameResults);
    }

}
