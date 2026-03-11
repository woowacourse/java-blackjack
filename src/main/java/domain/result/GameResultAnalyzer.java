package domain.result;

import domain.participant.Player;
import domain.result.dto.DealerGameResultDto;
import domain.result.dto.PlayerGameResultDto;
import domain.result.dto.GameResultDto;
import domain.participant.Dealer;
import domain.participant.Players;

import java.util.EnumMap;
import java.util.List;

public class GameResultAnalyzer {

    private GameResultAnalyzer() {
    }

    public static GameResultDto analyze(Players players, Dealer dealer) {
        List<PlayerGameResultDto> playerGameResultDtos = players.stream()
                .map(player -> judgePlayerGameResult(dealer, player))
                .toList();
        DealerGameResultDto dealerGameResultDto = makeDealerResult(playerGameResultDtos);

        return GameResultDto.of(dealerGameResultDto, playerGameResultDtos);
    }

    private static PlayerGameResultDto judgePlayerGameResult(Dealer dealer, Player player) {
        if (player.isBusted()) {
            return PlayerGameResultDto.of(player, GameResult.LOSS);
        }

        if (dealer.isBusted()) {
            return PlayerGameResultDto.of(player, GameResult.WIN);
        }

        int dealerResultScore = dealer.getResultScore();
        GameResult gameResult = judge(dealerResultScore, player.getResultScore());

        return PlayerGameResultDto.of(player, gameResult);
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

    private static DealerGameResultDto makeDealerResult(List<PlayerGameResultDto> playerGameResultDtos) {
        EnumMap<GameResult, Integer> dealerGameResult = new EnumMap<>(GameResult.class);
        List<GameResult> list = playerGameResultDtos.stream()
                .map(PlayerGameResultDto::gameResult)
                .map(GameResult::reverseResult)
                .toList();

        for (GameResult gameResult : list) {
            dealerGameResult.put(gameResult, dealerGameResult.getOrDefault(gameResult, 0) + 1);
        }
        return DealerGameResultDto.from(dealerGameResult);
    }

}
