package service;

import domain.game.Result;
import domain.game.GameResult;
import domain.participant.*;

import java.util.HashMap;
import java.util.Map;

public class BlackJackService {

    public BlackJackService() {
    }

    public Result calculateResult(Dealer dealer, Players players) {
        Map<Player, GameResult> playersResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            playersResult.put(player, calculatePlayerWinDefeatDraw(dealer, player));
        }

        return new Result(playersResult);
    }

    private GameResult calculatePlayerWinDefeatDraw(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (player.isBlackJack() && dealer.isBlackJack()) return GameResult.PUSH;
        if (player.isBlackJack()) return GameResult.BLACKJACK_WIN;
        if (player.getHand().isBust()) return GameResult.DEFEAT;
        if (dealer.getHand().isBust()) return GameResult.WIN;
        if (dealerTotalScore < playerTotalScore) return GameResult.WIN;
        if (dealerTotalScore > playerTotalScore) return GameResult.DEFEAT;
        return GameResult.PUSH;
    }
}
