package service;

import domain.Dealer;
import domain.Player;
import domain.Players;
import domain.ResultInfo;

import java.util.HashMap;
import java.util.Map;

public class BlackJackService {
    private final Map<String, ResultInfo> result;

    public BlackJackService() {
        this.result = new HashMap<>();
    }

    public Map<String, ResultInfo> calculateResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            result.put(player.getName(), calculateWinDefeatDraw(dealer, player));
        }
        return result;
    }

    //승패는 플레이어 기준
    private ResultInfo calculateWinDefeatDraw(Dealer dealer, Player player) {
        Integer dealerTotalScore = dealer.getTotalCardScore();
        Integer playerTotalScore = player.getTotalCardScore();
        if (dealerTotalScore < playerTotalScore) return ResultInfo.WIN;
        if (dealerTotalScore > playerTotalScore) return ResultInfo.DEFEAT;
        return ResultInfo.DRAW;
    }
}
