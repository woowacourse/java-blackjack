package domain.result;

import domain.participant.Player;
import domain.result.dto.DealerGameResult;
import domain.result.dto.PlayerGameResult;
import domain.result.dto.GameResultAnalysis;
import domain.participant.Dealer;
import domain.participant.Players;

import java.util.EnumMap;
import java.util.List;

//TODO player,dealer 상속 적용 후 개선 필요 (중복 if문)
public class GameResultAnalyzer {

    public static GameResultAnalysis analyze(Players players, Dealer dealer) {
        List<PlayerGameResult> playerGameResults = players.stream()
                .map(player -> judgePlayerGameResult(dealer, player))
                .toList();
        DealerGameResult dealerGameResult = makeDealerResult(playerGameResults);

        return GameResultAnalysis.of(dealerGameResult, playerGameResults);
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

    private static DealerGameResult makeDealerResult(List<PlayerGameResult> playerGameResults) {
        EnumMap<GameResult, Integer> dealerGameResult = new EnumMap<>(GameResult.class);
        List<GameResult> list = playerGameResults.stream()
                .map(PlayerGameResult::gameResult)
                .map(GameResult::reverseResult)
                .toList();

        for (GameResult gameResult : list) {
            dealerGameResult.put(gameResult, dealerGameResult.getOrDefault(gameResult, 0) + 1);
        }
        return DealerGameResult.from(dealerGameResult);
    }

}
