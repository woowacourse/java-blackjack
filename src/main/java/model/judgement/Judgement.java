package model.judgement;

import static model.judgement.ResultStatus.BLACKJACK;
import static model.judgement.ResultStatus.DRAW;
import static model.judgement.ResultStatus.LOSE;
import static model.judgement.ResultStatus.WIN;

import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Dealer;
import model.paticipant.Player;
import model.paticipant.Players;

public class Judgement {

    public static PlayerResult judgeByPlayer(Dealer dealer, Players players) {
        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, decide(dealer, player)));
        return new PlayerResult(result);
    }

    private static ResultStatus decide(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        if (dealer.isBlackjack()) {
            return decideWhenDealerBlackjack(player);
        }

        if (player.isBlackjack()) {
            return BLACKJACK;
        }

        return decideByScore(dealer, player);
    }

    private static ResultStatus decideWhenDealerBlackjack(Player player) {
        if (player.isBlackjack()) {
            return DRAW;
        }
        return LOSE;
    }

    private static ResultStatus decideByScore(Dealer dealer, Player player) {
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
}
