package domain.result;

import domain.result.dto.PlayerGameResult;
import domain.result.dto.GameResultAnalysisDto;
import domain.dealer.Dealer;
import domain.player.Players;

import java.util.List;

public class GameResultAnalyzer {

    public static GameResultAnalysisDto analyze(Players players, Dealer dealer) {

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

        return GameResultAnalysisDto.from(playerGameResults);
    }

}
