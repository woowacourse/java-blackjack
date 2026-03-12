package domain.game;

import domain.constant.Result;
import domain.dto.GameResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;

public class GameResultJudge {
    private GameResultJudge() {
    }

    public static List<GameResultDto> judge(Dealer dealer, Players players) {
        // TODO: 베팅 기능 추가 시 승/패/무 뿐 아니라 정산 금액까지 포함한 결과 생성 필요
        List<GameResultDto> results = new ArrayList<>();
        results.add(new GameResultDto(dealer.getName()));
        addPlayerResults(results, dealer, players);
        return results;
    }

    private static void addPlayerResults(List<GameResultDto> results, Dealer dealer, Players players) {
        for (Player player : players.getNonNaturalBlackJackPlayers()) {
            results.add(judgePlayer(player, dealer));
        }
    }

    private static GameResultDto judgePlayer(Player player, Dealer dealer) {
        Result result = calculateResult(player, dealer);
        return new GameResultDto(player.getName(), result);
    }

    private static Result calculateResult(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if (player.isBust()) {
            return Result.LOSE;
        }

        if (dealer.isBust()) {
            return Result.WIN;
        }

        if (playerScore > dealerScore) {
            return Result.WIN;
        }

        if (playerScore < dealerScore) {
            return Result.LOSE;
        }

        return Result.DRAW;
    }
}
