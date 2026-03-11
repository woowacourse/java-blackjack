package service;

import domain.game.Result;
import domain.game.ResultInfo;
import domain.participant.*;

import java.util.HashMap;
import java.util.Map;

public class BlackJackService {

    public BlackJackService() {
    }

    public Result calculateResult(Dealer dealer, Players players) {
        Map<Player, ResultInfo> playersResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            playersResult.put(player, calculatePlayerWinDefeatDraw(dealer, player));
        }

        return new Result(playersResult);
    }

    private ResultInfo calculatePlayerWinDefeatDraw(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (player.isBlackJack() && dealer.isBlackJack()) return ResultInfo.DRAW;
        if (player.isBlackJack()) return ResultInfo.BLACKJACK_WIN;
        if (player.getHand().isBust()) return ResultInfo.DEFEAT;
        if (dealer.getHand().isBust()) return ResultInfo.WIN;
        if (dealerTotalScore < playerTotalScore) return ResultInfo.WIN;
        if (dealerTotalScore > playerTotalScore) return ResultInfo.DEFEAT;
        return ResultInfo.DRAW;
    }
}
