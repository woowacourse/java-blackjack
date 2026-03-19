package domain.game;

import domain.constant.Result;
import dto.DealerResultDto;
import dto.GameResultDto;
import dto.PlayerResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class GameResultJudge {
    private GameResultJudge() {
    }

    public static GameResultDto judge(Dealer dealer, Players players) {
        List<PlayerResultDto> playerResults = new ArrayList<>();

        addPlayerResults(playerResults, dealer, players);

        double dealerProceeds = calculateDealerProceeds(playerResults);
        DealerResultDto dealerResult = new DealerResultDto(dealer.getName(), dealerProceeds);

        return new GameResultDto(dealerResult, playerResults);
    }

    private static void addPlayerResults(List<PlayerResultDto> results, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            results.add(judgePlayer(player, dealer));
        }
    }

    private static PlayerResultDto judgePlayer(Player player, Dealer dealer) {
        Result result = Result.from(player, dealer);
        double proceeds = player.calculateProceeds(result);
        return new PlayerResultDto(player.getName(), result, proceeds);
    }

    private static double calculateDealerProceeds(List<PlayerResultDto> results) {
        return -results.stream()
                .mapToDouble(PlayerResultDto::getProceeds)
                .sum();
    }
}