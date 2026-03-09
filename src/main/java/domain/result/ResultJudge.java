package domain.result;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

public class ResultJudge {
    private static final int BLACKJACK_LIMIT_NUMBER = 21;

    public Result calculateResult(Dealer dealer, Players players) {
        Result result = new Result();
        for (Player player : players.getPlayers()) {
            result.setPlayerResult(player.getName(), calculateWinDefeatDraw(dealer, player));
        }
        return result;
    }

    //승패는 플레이어 기준
    private ResultInfo calculateWinDefeatDraw(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (dealerTotalScore > BLACKJACK_LIMIT_NUMBER) return ResultInfo.WIN;
        if (playerTotalScore > BLACKJACK_LIMIT_NUMBER) return ResultInfo.DEFEAT;
        if (dealerTotalScore < playerTotalScore) return ResultInfo.WIN;
        if (dealerTotalScore > playerTotalScore) return ResultInfo.DEFEAT;
        return ResultInfo.DRAW;
    }
}
