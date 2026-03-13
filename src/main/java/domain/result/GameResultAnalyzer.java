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
            return PlayerGameResultDto.of(player, WinningStatus.LOSS);
        }

        if (dealer.isBusted()) {
            return PlayerGameResultDto.of(player, WinningStatus.WIN);
        }

        WinningStatus winningStatus = judgePlayerWinningStatusByScore(player, dealer);

        return PlayerGameResultDto.of(player, winningStatus);
    }

    private static WinningStatus judgePlayerWinningStatusByScore(Player player, Dealer dealer) {
        int playerScore = player.getResultScore();
        int dealerScore = dealer.getResultScore();
        if (playerScore < dealerScore) {
            return WinningStatus.LOSS;
        }
        if (playerScore > dealerScore) {
            return WinningStatus.WIN;
        }
        return WinningStatus.DRAW;
    }

    private static DealerGameResultDto makeDealerResult(List<PlayerGameResultDto> playerGameResultDtos) {
        EnumMap<WinningStatus, Integer> dealerGameResult = new EnumMap<>(WinningStatus.class);
        List<WinningStatus> list = playerGameResultDtos.stream()
                .map(PlayerGameResultDto::winningStatus)
                .map(WinningStatus::reverseResult)
                .toList();

        for (WinningStatus winningStatus : list) {
            dealerGameResult.put(winningStatus, dealerGameResult.getOrDefault(winningStatus, 0) + 1);
        }
        return DealerGameResultDto.from(dealerGameResult);
    }

}
