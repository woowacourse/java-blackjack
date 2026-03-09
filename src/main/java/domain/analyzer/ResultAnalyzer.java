package domain.analyzer;

import domain.GameResult;
import domain.analyzer.dto.PlayerGameResult;
import domain.analyzer.dto.ResultAnalysisDto;
import domain.dealer.Dealer;
import domain.player.Players;

import java.util.List;

public class ResultAnalyzer {

    public static ResultAnalysisDto analyze(Players players, Dealer dealer) {

        List<PlayerGameResult> playerGameResults = players.stream().map(player -> {

            if (player.isBusted()) {
                return PlayerGameResult.of(player, GameResult.LOSS);
            }

            if (dealer.isBusted()) {
                return PlayerGameResult.of(player, GameResult.WIN);
            }
    
            int dealerResultScore = dealer.getResultScore();
            GameResult gameResult = GameResult.judge(dealerResultScore, player.getResultScore());
            return PlayerGameResult.of(player, gameResult);
        }).toList();

        return ResultAnalysisDto.from(playerGameResults);
    }

}
