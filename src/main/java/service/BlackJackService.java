package service;

import domain.game.Result;
import domain.game.Outcome;
import domain.participant.*;
import domain.participant.player.Player;
import domain.participant.player.Players;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return determineResultByBlackJack(dealer, player)
                .or(() -> determineResultByBust(dealer, player))
                .orElseGet(() -> determineResultByScore(dealer, player));
    }

    private Optional<Outcome> determineResultByBlackJack(Dealer dealer, Player player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return Optional.of(Outcome.PUSH);
        }
        if (dealer.isBlackJack()) {
            return Optional.of(Outcome.DEFEAT);
        }
        if (player.isBlackJack()) {
            return Optional.of(Outcome.BLACKJACK_WIN);
        }
        return Optional.empty();
    }

    private Optional<Outcome> determineResultByBust(Dealer dealer, Player player) {
        if (player.getHand().isBust()) {
            return Optional.of(Outcome.DEFEAT);
        }
        if (dealer.getHand().isBust()) {
            return Optional.of(Outcome.WIN);
        }
        return Optional.empty();
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
