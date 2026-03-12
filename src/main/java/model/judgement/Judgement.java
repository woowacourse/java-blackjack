package model.judgement;

import static model.judgement.GameStatus.DRAW;
import static model.judgement.GameStatus.LOSE;
import static model.judgement.GameStatus.WIN;

import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;

public class Judgement {

    public static PlayerResult judgeByPlayer(Dealer dealer, Players players) {
        Map<Player, GameStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, decide(dealer, player)));
        return new PlayerResult(result);
    }

    private static GameStatus decide(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        return decideByScore(dealer, player);
    }

    private static GameStatus decideByScore(Dealer dealer, Player player) {
        int dealerScore = dealer.calculateTotalScore();
        int playerScore = player.calculateTotalScore();

        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public static DealerResult judgeByDealer(PlayerResult playerResult) {
        int dealerWinCount = playerResult.countByStatus(GameStatus.LOSE);
        int dealerLoseCount = playerResult.countByStatus(GameStatus.WIN);
        int dealerDrawCount = playerResult.countByStatus(GameStatus.DRAW);
        return new DealerResult(dealerWinCount, dealerLoseCount, dealerDrawCount);
    }
}
