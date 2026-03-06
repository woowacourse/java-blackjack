package service;

import domain.*;

public class BlackJackService {
    private final Result result;

    public BlackJackService(Result result) {
        this.result = result;
    }

    public Result calculateResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            result.setEntry(player.getName(), calculateWinDefeatDraw(dealer, player));
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
