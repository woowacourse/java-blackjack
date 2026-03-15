package domain.game;

import domain.constant.Result;
import dto.GameResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class GameResultJudge {
    private GameResultJudge() {
    }

    public static List<GameResultDto> judge(Dealer dealer, Players players) {
        List<GameResultDto> results = new ArrayList<>();

        addPlayerResults(results, dealer, players);

        double dealerProceeds = calculateDealerProceeds(results);
        results.add(0, new GameResultDto(dealer.getName(), dealerProceeds));

        return results;
    }

    private static void addPlayerResults(List<GameResultDto> results, Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            results.add(judgePlayer(player, dealer));
        }
    }

    private static GameResultDto judgePlayer(Player player, Dealer dealer) {
        Result result = Result.from(player, dealer);
        double proceeds = player.calculateProceeds(result);
        return new GameResultDto(player.getName(), result, proceeds);
    }

    private static double calculateDealerProceeds(List<GameResultDto> results) {
        return -results.stream()
                .mapToDouble(GameResultDto::getProceeds)
                .sum();
    }
}