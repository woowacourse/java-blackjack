package domain.analyzer;

import domain.analyzer.dto.PlayerGameResult;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.dealer.Dealer;
import domain.player.Players;

import java.util.List;

public class ResultAnalyzer {

    private ResultAnalyzer() {

    }

    public static ResultAnalysisDto analyze(Players players, Dealer dealer) {
        List<PlayerGameResult> playerGameResults = players.stream()
                .map(player -> PlayerGameResult.of(player, GameResult.judge(dealer, player)))
                .toList();
        return ResultAnalysisDto.from(playerGameResults);
    }

}
