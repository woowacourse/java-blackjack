package service;

import domain.game.Result;
import domain.game.ResultInfo;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class BlackJackService {
    private static final int BLACKJACK_LIMIT_NUMBER = 21;
    private final Result result;

    public BlackJackService(Result result) {
        this.result = result;
    }

    public Result calculateResult(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            result.setPlayerResult(player.getName(), calculatePlayerWinDefeatDraw(dealer, player));
        }
        return result;
    }

    private ResultInfo calculatePlayerWinDefeatDraw(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (dealerTotalScore > BLACKJACK_LIMIT_NUMBER) return ResultInfo.WIN;
        if (playerTotalScore > BLACKJACK_LIMIT_NUMBER) return ResultInfo.DEFEAT;
        if (dealerTotalScore < playerTotalScore) return ResultInfo.WIN;
        if (dealerTotalScore > playerTotalScore) return ResultInfo.DEFEAT;
        return ResultInfo.DRAW;
    }
}
