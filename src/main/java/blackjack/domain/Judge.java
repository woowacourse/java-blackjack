package blackjack.domain;

import static blackjack.domain.result.GameResult.BLACKJACK_WIN;
import static blackjack.domain.result.GameResult.PLAYER_LOSE;
import static blackjack.domain.result.GameResult.PLAYER_WIN;
import static blackjack.domain.result.GameResult.PUSH;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerGameResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayerResults;
import java.util.stream.Collectors;

public class Judge {

    public PlayerResults calculatePlayerResults(Dealer dealer, Players players) {
        return new PlayerResults(players.getPlayers().stream()
                .map(player -> new PlayerResult(player.getName(), calculatePlayerResult(dealer, player)))
                .toList());
    }

    public PlayerGameResult calculatePlayerGameResult(Dealer dealer, Players players) {
        return new PlayerGameResult(players.getPlayers().stream()
                .collect(Collectors.toMap(player -> player, player -> calculatePlayerResult(dealer, player))));
    }

    public GameResult calculatePlayerResult(Dealer dealer, Player player) {
        Score playerScore = player.calculateHandScore();
        Score dealerScore = dealer.calculateHandScore();

        if (player.hasBlackJackHand() && !dealer.hasBlackJackHand()) {
            return BLACKJACK_WIN;
        }
        if (player.isBusted()) {
            return PLAYER_LOSE;
        }
        if (dealer.isBusted() || playerScore.isAbove(dealerScore)) {
            return PLAYER_WIN;
        }
        if (playerScore.equals(dealerScore)) {
            return PUSH;
        }
        return PLAYER_LOSE;
    }
}
