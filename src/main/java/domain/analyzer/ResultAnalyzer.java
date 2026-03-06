package domain.analyzer;

import domain.GameResult;
import domain.analyzer.dto.PlayerGameResult;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.dealer.Dealer;
import domain.player.Players;

import java.util.List;

public class ResultAnalyzer {

    private static final ResultAnalyzer INSTANCE = new ResultAnalyzer();

    private ResultAnalyzer() {
    }

    public static ResultAnalyzer getInstance() {
        return INSTANCE;
    }

    public ResultAnalysisDto analyze(Players players, Dealer dealer) {
        int dealerResultScore = dealer.getResultScore();

        List<PlayerGameResult> playerGameResults = players.stream().map(player -> {
            if (player.isBusted()) {
                return PlayerGameResult.of(player, GameResult.LOSS);
            }

            GameResult gameResult = GameResult.judge(dealerResultScore, player.getResultScore());
            return PlayerGameResult.of(player, gameResult);
        }).toList();

        return ResultAnalysisDto.from(playerGameResults);
    }

    public ResultAnalysisDto analyzeV2(Players players, Dealer dealer) {
        List<PlayerGameResult> playerGameResults = players.stream()
                .map(player -> PlayerGameResult.of(player, GameResult.judgeV2(dealer, player)))
                .toList();
        return ResultAnalysisDto.from(playerGameResults);
    }

}
