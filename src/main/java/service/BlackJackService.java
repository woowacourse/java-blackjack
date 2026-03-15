package service;

import domain.game.Result;
import domain.game.Outcome;
import domain.participant.*;
import domain.participant.player.Player;
import domain.participant.player.Players;

import java.util.HashMap;
import java.util.Map;

public class BlackJackService {

    public BlackJackService() {
    }

    public Result calculateResult(Dealer dealer, Players players) {
        Map<Player, Outcome> playersResult = new HashMap<>();
        for (Player player : players.getPlayers()) {
            playersResult.put(player, calculatePlayerResults(dealer, player));
        }

        return new Result(playersResult);
    }

    private Outcome calculatePlayerResults(Dealer dealer, Player player) {
        Outcome blackJackResult = determineResultByBlackJack(dealer, player);
        if (blackJackResult != null) {
            return blackJackResult;
        }
        Outcome bustResult = determineResultByBust(dealer, player);
        if (bustResult != null) {
            return bustResult;
        }
        return determineResultByScore(dealer, player);
    }

    private Outcome determineResultByBlackJack(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return Outcome.PUSH;
        }
        if (dealer.isBlackJack()) {
            return Outcome.DEFEAT;
        }
        if (player.isBlackJack()) {
            return Outcome.BLACKJACK_WIN;
        }
        return null;
    }

    private Outcome determineResultByBust(Dealer dealer, Player player) {
        if (player.getHand().isBust()) {
            return Outcome.DEFEAT;
        }
        if (dealer.getHand().isBust()) {
            return Outcome.WIN;
        }
        return null;
    }

    private Outcome determineResultByScore(Dealer dealer, Player player) {
        int dealerTotalScore = dealer.getTotalCardScore();
        int playerTotalScore = player.getTotalCardScore();

        if (dealerTotalScore < playerTotalScore) {
            return Outcome.WIN;
        }
        if (dealerTotalScore > playerTotalScore) {
            return Outcome.DEFEAT;
        }
        return Outcome.PUSH;
    }
}
