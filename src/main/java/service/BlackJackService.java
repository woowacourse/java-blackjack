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
            playersResult.put(player, calculatePlayerResults(dealer, player));
        }

        return new Result(playersResult);
    }

    private GameResult calculatePlayerResults(Dealer dealer, Player player) {
        GameResult blackJackResult = determineResultByBlackJack(dealer, player);
        if (blackJackResult != null) {
            return blackJackResult;
        }
        GameResult bustResult = determineResultByBust(dealer, player);
        if (bustResult != null) {
            return bustResult;
        }
        return determineResultByScore(dealer, player);
    }

    private GameResult determineResultByBlackJack(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return GameResult.PUSH;
        }
        if (dealer.isBlackJack()) {
            return GameResult.DEFEAT;
        }
        if (player.isBlackJack()) {
            return GameResult.BLACKJACK_WIN;
        }
        return null;
    }

    private GameResult determineResultByBust(Dealer dealer, Player player) {
        if (player.getHand().isBust()) {
            return GameResult.DEFEAT;
        }
        if (dealer.getHand().isBust()) {
            return GameResult.WIN;
        }
        return null;
    }

    private GameResult determineResultByScore(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (dealerTotalScore < playerTotalScore) {
            return GameResult.WIN;
        }
        if (dealerTotalScore > playerTotalScore) {
            return GameResult.DEFEAT;
        }
        return GameResult.PUSH;
    }
}
