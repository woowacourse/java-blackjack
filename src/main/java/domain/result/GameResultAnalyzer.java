package domain.result;

import domain.player.Player;
import domain.result.dto.PlayerGameResult;
import domain.result.dto.GameResultAnalysisDto;
import domain.dealer.Dealer;
import domain.player.Players;

import java.util.List;

//TODO player,dealer 상속 적용 후 개선 필요 (중복 if문)
public class GameResultAnalyzer {

    public static GameResultAnalysisDto analyze(Players players, Dealer dealer) {
        List<PlayerGameResult> playerGameResults = players.stream()
                .map(player -> judgePlayerGameResult(dealer, player))
                .toList();

        return GameResultAnalysisDto.from(playerGameResults);
    }

    private static PlayerGameResult judgePlayerGameResult(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return PlayerGameResult.of(player, GameResult.LOSS);
        }

        if (dealer.isBusted()) {
            return PlayerGameResult.of(player, GameResult.WIN);
        }

        int dealerResultScore = dealer.getResultScore();
        GameResult gameResult = judge(dealerResultScore, player.getResultScore());

        return PlayerGameResult.of(player, gameResult);
    }

    private static GameResult judge(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return GameResult.LOSS;
        }

        if (dealerScore == playerScore) {
            return GameResult.DRAW;
        }

        return GameResult.WIN;
    }
}
